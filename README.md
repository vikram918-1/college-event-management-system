# College Event Organizer

A console-based Java application for managing college events, participant registrations, event schedules, attendance, and rewards. The project demonstrates object-oriented programming, generics, lambda expressions, custom exceptions, collections, and CSV file handling.

## Features

- **Participant management:** Load participant records from a CSV file, add individual participants, and create teams.
- **Event management:** Work with workshops, hackathons, technical competitions, and cultural festivals.
- **Registration:** Register participants for events, view registrations, and cancel registrations.
- **Venue and scheduling:** Allocate venues to events and maintain event schedule information.
- **Attendance tracking:** Record participant attendance, check-in and check-out times, and calculate attendance duration.
- **Rewards management:** Create and assign cash, merchandise, and certificate rewards.
- **Reward eligibility:** Validate winning positions and reward-value limits using reward policies.
- **Winner management:** Record event winners and view assigned rewards.
- **Certificate generation:** Generate participation certificate text files for registered participants.

## Technologies and concepts

- Java
- Object-oriented programming: abstraction, encapsulation, inheritance, and polymorphism
- Generics
- Java Collections Framework
- Lambda expressions
- Exception handling with custom exceptions
- File handling and CSV input
- Java Date and Time API

## Project structure

```text
college-event-management-system/
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

## Clone the repository

```bash
git clone https://github.com/vikram918-1/college-event-management-system.git
cd college-event-management-system
```

## Compile and run the application

Run these commands from the project root (the directory containing `participants.csv`).

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

The program reads initial participant records from `participants.csv` in the working directory. Generated certificate text files are saved in a `certificates/` directory when certificates are generated.

## Sample participant data

`participants.csv` contains sample participant records for demonstrating CSV loading. Replace them with your own test data when experimenting. Do not commit real personal information.

## Team

This was developed as a four-member semester project:

- Ofera Levina
- Saaral
- Trinisha
- Vikram

### Individual contribution

**Vikram — Rewards Management Module**

- Developed the abstract `Reward` class and specialised classes for cash, merchandise, and certificate rewards.
- Implemented generic reward management using `RewardManager<T extends Reward>`.
- Implemented reward assignment and eligibility validation through `RewardPolicy`, including winning-position requirements and reward-value limits.
- Integrated reward assignment with event and participant data, with custom exception handling for invalid reward assignments.

The project was developed collaboratively; the contribution above identifies Vikram's primary area of responsibility.

## Notes

This is an academic project. It is a command-line demonstration and does not include a production database, web interface, or real payment processing. Cash reward payment is simulated within the application.
