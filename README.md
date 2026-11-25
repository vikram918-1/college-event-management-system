# College Event Organizer

A console-based Java application for managing college events, participant registrations, event schedules, attendance, and rewards. The project demonstrates object-oriented programming, generics, functional interfaces, lambda expressions, custom exceptions, collections, and CSV file handling.

## Features

- **Participant management:** Load participant records from a CSV file, add individuals, and create teams.
- **Event management:** Work with workshops, hackathons, technical competitions, and cultural festivals.
- **Registration:** Register participants for events, view registrations, and cancel registrations.
- **Event operations:** Manage venues and schedules, and record attendance.
- **Rewards management:** Create and manage cash, merchandise, and certificate rewards; assign rewards according to eligibility policies; and filter rewards using a custom functional interface.
- **Certificate generation:** Generate text-based participation certificates for assigned certificate rewards.

## Technologies and concepts

- Java
- Object-oriented programming: abstraction, inheritance, encapsulation, and polymorphism
- Generics
- Functional interfaces and lambda expressions
- Collections
- Exception handling with custom exceptions
- File handling and CSV input
- Java date and time API

## Project structure

```text
EventOrganization/
├── com/
│   └── eventorg/
│       ├── Main.java
│       ├── event/
│       ├── exception/
│       ├── participant/
│       ├── reward/
│       └── util/
├── participants.csv
├── README.md
└── .gitignore
```

## Requirements

- JDK 8 or later
- A terminal or Java IDE

## Run the application

Run these commands from the project root (the directory containing `participants.csv`):

### macOS / Linux

```bash
mkdir -p out
javac -d out $(find com -name "*.java")
java -cp out com.eventorg.Main
```

### Windows PowerShell

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out com.eventorg.Main
```

The program reads initial participant records from `participants.csv` in the working directory. Generated certificate text files are written to a `certificates/` directory when certificates are generated.

## Sample participant data

`participants.csv` contains fictional sample records for demonstrating CSV loading. Replace them with your own test data when experimenting. Do not commit real personal information.

## Team

This was developed as a four-member semester project:

- Ofera Levina
- Saaral
- Trinisha
- Vikram

### Individual contribution

**Vikram — Rewards Management Module**

- Developed reward classes for cash, merchandise, and certificates.
- Implemented reward assignment and eligibility validation through reward policies.
- Implemented reward filtering using a custom functional interface.
- Worked with Java generics in the reward management functionality.

The project was developed collaboratively; the contribution above identifies Vikram's primary area of responsibility.

## Notes

This is an academic project. It is a command-line demonstration and does not include a production database, web interface, or real payment processing. Cash reward payment status is simulated within the application.
