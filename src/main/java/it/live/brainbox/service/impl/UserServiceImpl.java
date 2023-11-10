package it.live.brainbox.service.impl;

import it.live.brainbox.config.SecurityConfiguration;
import it.live.brainbox.entity.Attachment;
import it.live.brainbox.entity.User;
import it.live.brainbox.entity.enums.SystemRoleName;
import it.live.brainbox.exception.MainException;
import it.live.brainbox.exception.NotFoundException;
import it.live.brainbox.mapper.UserMapper;
import it.live.brainbox.payload.ApiResponse;
import it.live.brainbox.payload.PageSender;
import it.live.brainbox.payload.UserDTO;
import it.live.brainbox.repository.AttachmentRepository;
import it.live.brainbox.repository.UserRepository;
import it.live.brainbox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final AttachmentRepository attachmentRepository;
    private static final String uploadDirectory = "YUKLANMALAR";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));
    }


    @Override
    public ResponseEntity<ApiResponse> updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Bunday user topilmadi"));
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setUniqueId(userDTO.getUniqueId());
        user.setImageUrl(userDTO.getImageUrl());
        userRepository.save(user);
        return ResponseEntity.ok(ApiResponse.builder().message("Malumotlar yangilandi").status(200).build());
    }

    @Override
    public User getUserById(Long userId) {
        if (userId != null) {
            return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("Bunday user mavjud emas"));
        }
        return SecurityConfiguration.getOwnSecurityInformation();
    }

    @Override
    public PageSender<List<User>> getAllUserByPage(int page, int size) {
        List<User> list = userRepository.findAllBySystemRoleName(SystemRoleName.ROLE_ADMIN);
        return new PageSender<>((long) userRepository.findAllBySystemRoleName(SystemRoleName.ROLE_USER).size(), list);
    }

    @Override
    public Attachment uploadFile(MultipartHttpServletRequest filelar) throws IOException {
        try {
            Iterator<String> fileNames = filelar.getFileNames();
            MultipartFile file = filelar.getFile(fileNames.next());
            if (file != null) {
                String originalFilename = file.getOriginalFilename();
                assert originalFilename != null;
                String[] split = originalFilename.split("\\.");
                String name = UUID.randomUUID().toString() + "." + split[split.length - 1];
                Attachment save = attachmentRepository.save(Attachment.builder().fileOriginalName(originalFilename).size(file.getSize()).contentType(file.getContentType()).name(name).build());
                Path path = Paths.get(uploadDirectory + File.separator + name);
                Files.copy(file.getInputStream(), path);
                return save;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
