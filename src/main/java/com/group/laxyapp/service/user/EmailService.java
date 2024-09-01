//package com.group.laxyapp.service.user;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//@Service
//@RequiredArgsConstructor
//public class EmailService {
//
//    private final JavaMailSender javaMailSender;
//    private static final String senderEmail = "dasom20301794@gmail.com"; // 발신자 이메일 설정
//    private static int confirmationCode; // 인증 코드
//
//    // 인증 코드 생성 메서드
//    private static int generateConfirmationCode() {
//        return (int) ((Math.random() * (90000)) + 100000); // 6자리 랜덤 인증 코드 생성
//    }
//
//    // 이메일 양식 작성 메서드
//    private MimeMessage createEmail(String email) {
//        int code = generateConfirmationCode(); // 인증 코드 생성
//        MimeMessage message = javaMailSender.createMimeMessage();
//
//        try {
//            message.setFrom(senderEmail); // 발신자 설정
//            message.setRecipients(MimeMessage.RecipientType.TO, email); // 수신자 설정
//            message.setSubject("[WalkingMate] 회원가입을 위한 이메일 인증"); // 제목 설정
//            String body = "<h1>안녕하세요. Walking Mate입니다.</h1><br>";
//            body += "<h3>회원가입을 완료하려면 아래의 인증 코드를 입력해주세요.</h3><br>";
//            body += "<div align='center' style='border:1px solid black; font-family:verdana;'>";
//            body += "<h2>회원가입 인증 코드: " + code + "</h2>";
//            body += "</div><br>";
//            body += "<h3>감사합니다.</h3>";
//            message.setText(body, "UTF-8", "html");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//        return message;
//    }
//
//    // 이메일 발송 메서드
//    public int sendEmailConfirmation(String email) {
//        MimeMessage message = createEmail(email); // 이메일 생성
//        javaMailSender.send(message); // 이메일 전송
//        return confirmationCode; // 생성된 인증 코드 반환
//    }
//}