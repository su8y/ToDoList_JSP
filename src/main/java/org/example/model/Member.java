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

    @Override
    public String toString() {
        return "Member{" +
                "mId:'" + mId + '\'' +
                ", mPw:'" + mPw + '\'' +
                ", mName:'" + mName + '\'' +
                ", mEmail:'" + mEmail + '\'' +
                '}';
    }
}
