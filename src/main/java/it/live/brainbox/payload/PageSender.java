package it.live.brainbox.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PageSender<T> {
    private Long allCountObject;
    private T list;
}
