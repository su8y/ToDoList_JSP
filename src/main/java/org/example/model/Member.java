package org.example.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    String mId;
    String mPw;
    String mName;
    String mEmail;
}
