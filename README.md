# Aurora Skin Care CLI System

## Overview

Aurora Skin Care CLI System is a command-line-based Java application designed to manage patient appointments in a dermatology clinic. It allows patients to book, update, view, search, and cancel appointments while ensuring validation and conflict prevention. Additionally, administrators can generate invoices after treatment completion.

## Features

- **Appointment Management**: Book, update, view, search, and cancel appointments.
- **Patient Information Handling**: Securely stores patient details (name, NIC, email, contact number).
- **Appointment Validation**: Prevents duplicate bookings for the same dermatologist, date, and time.
- **Invoice Generation**: Generates invoices after treatment completion.
- **User-Friendly CLI**: Provides a simple command-line interface for easy navigation.
- **Data Persistence**: Utilizes data structures to manage appointments efficiently.

## Assumptions

- Patients have already paid the registration fee when making an appointment.
- Administrators can generate invoices upon completing the treatment process.

## Technologies Used

- **Programming Language**: Java
- **Data Structures**: ArrayList for managing appointments
- **OOP Concepts**: Encapsulation, Inheritance, Polymorphism, and Abstraction
- **Validation Techniques**: Regex-based input validation
- **Date and Time Handling**: `LocalDate` and `LocalTime` for scheduling appointments

## Installation & Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/AuroraSkinCareCLI.git
   ```
2. Navigate to the project directory:
   ```bash
   cd AuroraSkinCareCLI
   ```
3. Compile the Java files:
   ```bash
   javac -d bin src/*.java
   ```
4. Run the application:
   ```bash
   java -cp bin Main
   ```

## Usage

1. Follow the CLI prompts to navigate the system.
2. Enter valid patient details and select an available appointment slot.
3. Use the admin features to update, search, or generate invoices as needed.

## Future Enhancements

- Implement a database for persistent storage.
- Add a graphical user interface (GUI).
- Integrate email notifications for appointment confirmations.

## License

This project is licensed under the MIT License.

