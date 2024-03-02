## 이미지 업로드를 소켓으로 하는 원리 
(1) Front 에서 이미지 MultipartFile을 Base64로 인코딩 후 해당 Base64 Text를 백엔드로 전송 
(2) 전송 받은 Base64 내용을 백엔드에서 임시 파일로 변환 후 S3로 전송
(3) S3로 전송 완료 시, 해당 임시 파일을 삭제
(4) S3에서 이미지 URL 반환 받아서 프론트 엔드로 전달 

## 시연 영상
https://github.com/dalcheonroadhead/RealTimeChatServer/assets/102154788/fd408310-8d82-438c-b398-c7fd6121b517

