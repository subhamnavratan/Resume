A backend Spring Boot + MongoDB project to manage and display resumes.
It supports fetching resume details by name and rendering them on a clean frontend (HTML/JS).

*****************Features **********************
Resume API with Spring Boot + MongoDB
REST Endpoints to fetch resume data by name
Data Model includes: Contact Details,Education Details,Achievements (LeetCode, GFG, etc.)
Frontend (HTML + JavaScript) for displaying sections like:About,Contact,Education,Skills,Projects,Achievements

********************* Tech Stack ***********************
Backend: Java 21, Spring Boot
Database: MongoDB Atlas
Frontend: HTML, CSS, JavaScript
Deployment : Render (backend )

Application.properties***
spring.application.name=RESUME
server.port=${PORT:8080}
server.address=0.0.0.0
spring.data.mongodb.uri=${MONGO_URI}

APIs******
Base Api : https://resume-27.onrender.com
GET /resume/{name} → Get resume by name
POST /resume → Create new resume
PUT /resume/{name} → Update resume by name
DELETE /resume/{name} → Delete resume by name
GET /resume/{name}/contact → Get contact details
PUT /resume/{name}/contact → Update contact details
GET /resume/{name}/education → Get education details
PUT /resume/{name}/education → Update education details
GET /resume/{name}/skills → Get skills
PUT /resume/{name}/skills → Update skills
GET /resume/{name}/projects → Get projects
PUT /resume/{name}/projects → Update projects
GET /resume/{name}/achievement → Get achievement
PUT /resume/{name}/achievement → Update achievement
GET /resume/{name}/about → Get about section
PUT /resume/{name}/about → Update about section
