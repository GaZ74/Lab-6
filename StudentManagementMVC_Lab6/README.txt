STUDENT INFORMATION:
Name: Dang Hoang Minh Long
Student ID: ITCSIU24054
Class: Web Application Development_S1_2025-26_G01_lab02

COMPLETED EXERCISES:
[x] Exercise 1: Database & User Model
[x] Exercise 2: User Model & DAO
[x] Exercise 3: Login/Logout Controllers
[x] Exercise 4: Views & Dashboard
[x] Exercise 5: Authentication Filter
[x] Exercise 6: Admin Authorization Filter
[x] Exercise 7: Role-Based UI
[x] Exercise 8: Change Password

AUTHENTICATION COMPONENTS:
- Models: User.java, reused Student.java
- DAOs: UserDAO.java, reused StudentDAO.java
- Controllers: LoginController.java, LogoutController.java, DashboardController.java, reused StudentController.java, ChangePasswordController.java
- Filters: AuthFilter.java, AdminFilter.java
- Views: login.jsp, dashboard.jsp, updated student-list.jsp, reused student-form.jsp, change-password.jsp

TEST CREDENTIALS:
Admin:
- Username: admin
- Password: password123

Regular User:
- Username: john
- Password: password123

FEATURES IMPLEMENTED:
- User authentication with BCrypt
- Session management
- Login/Logout functionality
- Dashboard with statistics
- Authentication filter for protected pages
- Admin authorization filter
- Role-based UI elements
- Password security

SECURITY MEASURES:
- BCrypt password hashing
- Session regeneration after login
- Session timeout (30 minutes)
- SQL injection prevention (PreparedStatement)
- Input validation
- XSS prevention (JSTL escaping)

KNOWN ISSUES:
- None

BONUS FEATURES:
- Ability to change user/admin's password (in dashboard)

TIME SPENT: Approximate 6 hours

