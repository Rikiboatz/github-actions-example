package com.example.cicd.utils;

public class StringUtil {
    
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public static boolean isValidMobileNo(String mobileNo) {
        String mobileNoRegex = "^(\\+66|0)\\d{9}$"; // ตัวอย่างสำหรับเบอร์โทรศัพท์ในประเทศไทย
        return mobileNo != null && mobileNo.matches(mobileNoRegex);
    }

    public static boolean isValidUsername(String username) {
        String usernameRegex = "^[a-zA-Z0-9._-]{3,20}$"; // ตัวอย่างสำหรับชื่อผู้ใช้ที่มีความยาว 3-20 ตัวอักษร
        return username != null && username.matches(usernameRegex);
    }

    public static boolean isValidPassword(String password) {
        // รหัสผ่านต้องมีความยาวอย่างน้อย 8 ตัวอักษร และประกอบด้วยตัวอักษรพิมพ์ใหญ่, พิมพ์เล็ก, ตัวเลข, และอักขระพิเศษ
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(passwordRegex);
    }
}
