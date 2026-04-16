package com.liku.pdfbuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserForm {

    // 👤 Basic Details
    private String name;
    private String email;
    private String mobile;
    private String gender;
    private String dob;

    // 🏠 Address
    private String state;
    private String district;
    private String address;

    // 🖼 Images (Base64)
    private String photo;
    private String signature;

}
