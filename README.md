# Developer : Basavaraj kokane

# Doctor-Patient Portal

A comprehensive web-based healthcare management system built with Java, JSP, Servlets, and MySQL. This portal facilitates seamless interaction between patients, doctors, and administrators.

## 🌟 Features

### Patient Features
- **User Registration & Login**: Secure patient registration and authentication system
- **Appointment Booking**: Book appointments with available doctors
- **View Appointments**: Track appointment status and history
- **Profile Management**: Update personal information

### Doctor Features
- **Doctor Login**: Secure authentication for medical professionals
- **Patient Management**: View and manage patient appointments
- **Profile Management**: Update professional information and qualifications
- **Appointment Comments**: Add medical comments and notes for appointments

### Admin Features
- **Admin Dashboard**: Comprehensive administrative control panel
- **Doctor Management**: Add, edit, and manage doctor profiles
- **Specialist Management**: Manage medical specializations
- **Patient Overview**: Monitor patient registrations and appointments

## 🚀 Technology Stack

- **Frontend**: JSP, HTML5, CSS3, Bootstrap 5, JavaScript
- **Backend**: Java Servlets, JSP
- **Database**: MySQL 8.0 / H2 Database (for development)
- **Build Tool**: Maven
- **Server**: Apache Tomcat 7+
- **IDE**: Eclipse (project configured)

## 📋 Prerequisites

Before running this application, ensure you have:

- Java JDK 8 or higher
- Apache Tomcat 7 or higher
- Maven 3.6+
- MySQL 5.7+ (or H2 for development)
- A modern web browser

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/doctor-patient-portal.git
cd doctor-patient-portal
```

### 2. Database Setup

#### Option A: MySQL Setup
1. Install MySQL and create a database:
```sql
CREATE DATABASE hospital;
```

2. Import the database schema:
```bash
mysql -u root -p hospital < db/schema.sql
```

3. Set up environment variables (see Environment Configuration below)

#### Option B: H2 Database (Development)
The project includes H2 database dependency for easy development setup. No additional database installation required.

### 3. Environment Configuration

Create environment variables for database connection:

**Windows PowerShell:**
```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/hospital"
$env:DB_USER="root"
$env:DB_PASS="yourpassword"
```

**Windows CMD:**
```cmd
set DB_URL=jdbc:mysql://localhost:3306/hospital
set DB_USER=root
set DB_PASS=yourpassword
```

**Linux/Mac:**
```bash
export DB_URL="jdbc:mysql://localhost:3306/hospital"
export DB_USER="root"
export DB_PASS="yourpassword"
```

### 4. Build and Deploy

#### Using Maven:
```bash
# Clean and compile
mvn clean compile

# Build WAR file
mvn clean package

# Run with embedded Tomcat
mvn tomcat7:run
```

#### Manual Deployment:
1. Build the project: `mvn clean package`
2. Copy `target/Doctor-Patient-Portal.war` to Tomcat's `webapps` directory
3. Start Tomcat server
4. Access the application at `http://localhost:8080/Doctor-Patient-Portal`

## 🏗️ Project Structure

```
Doctor-Patient-Portal/
├── src/main/
│   ├── java/com/hms/
│   │   ├── admin/servlet/          # Admin functionality servlets
│   │   ├── dao/                    # Data Access Objects
│   │   ├── db/                     # Database connection utilities
│   │   ├── doctor/servlet/         # Doctor functionality servlets
│   │   └── entity/                 # Entity classes
│   └── webapp/
│       ├── admin/                  # Admin JSP pages
│       ├── component/              # Reusable components
│       ├── doctor/                 # Doctor JSP pages
│       ├── img/                    # Images and assets
│       └── WEB-INF/                # Web configuration
├── db/
│   └── schema.sql                  # Database schema
├── pom.xml                         # Maven configuration
└── README.md                       # This file
```

## 🔐 Default Access Credentials

### Admin Access
- URL: `/admin_login.jsp`
- Username: `admin@hospital.com`
- Password: `admin123`

### Sample Doctor Login
- URL: `/doctor_login.jsp`
- Username: `doctor@hospital.com`
- Password: `doctor123`

*Note: Update these credentials in production environment*

## 🌐 Application URLs

- **Home Page**: `http://localhost:8080/Doctor-Patient-Portal/`
- **Patient Registration**: `http://localhost:8080/Doctor-Patient-Portal/signup.jsp`
- **Patient Login**: `http://localhost:8080/Doctor-Patient-Portal/user_login.jsp`
- **Doctor Login**: `http://localhost:8080/Doctor-Patient-Portal/doctor_login.jsp`
- **Admin Login**: `http://localhost:8080/Doctor-Patient-Portal/admin_login.jsp`

## 📊 Database Schema

The application uses the following main entities:

- **users**: Patient information and authentication
- **doctors**: Doctor profiles and credentials
- **specialists**: Medical specializations
- **appointments**: Appointment bookings and management

See `db/schema.sql` for complete database structure.

## 🛡️ Security Features

- Password encryption for user authentication
- Session management for secure access
- Role-based access control (Patient, Doctor, Admin)
- SQL injection prevention through prepared statements

## 🧪 Testing

Run tests with Maven:
```bash
mvn test
```

## 📝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## 🐛 Known Issues

- Date picker compatibility with older browsers
- Mobile responsiveness needs improvement for admin panel

## 📋 TODO

- [ ] Implement email notifications for appointments
- [ ] Add payment gateway integration
- [ ] Enhance mobile responsiveness
- [ ] Add appointment reminder system
- [ ] Implement doctor availability calendar

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👥 Authors

- **Basavraju kokane** - *Initial work* - [https://basavaraj1229.github.io/Portfolio-Profile/)

## 🤝 Acknowledgments

- Bootstrap for responsive UI components
- Apache Tomcat for servlet container
- MySQL for database management
- Maven for project management

## 📞 Support

For support and questions, please create an issue in the GitHub repository or contact [ https://mail.google.com/mail/u/0/?fs=1&to=rajukokane61@gmail.com&tf=cm]

---

**⭐ If you find this project useful, please give it a star!**
