package it.live.brainbox.service;

import it.live.brainbox.entity.Attachment;
import it.live.brainbox.entity.User;
import it.live.brainbox.payload.ApiResponse;
import it.live.brainbox.payload.PageSender;
import it.live.brainbox.payload.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface UserService {


    ResponseEntity<ApiResponse> updateUser(Long userId, UserDTO userDTO);

    User getUserById(Long userId);

    PageSender<List<User>> getAllUserByPage(int page, int size);

    Attachment uploadFile(MultipartHttpServletRequest file) throws IOException;
}
