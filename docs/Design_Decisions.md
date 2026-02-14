# Design Decisions

## 1. Layered Architecture
Separated entity, service, and utility layers to ensure separation of concerns.

## 2. Singleton Pattern
Used for IdGenerator to ensure single instance across application.

## 3. Strategy Pattern
Used for billing calculations (Standard, Insurance, Emergency).
Allows adding new billing logic without modifying Bill class.

## 4. Factory Pattern
Encapsulates bill creation logic in BillFactory.

## 5. Immutable Class
BillSummary implemented as immutable for thread safety.

## 6. Deep Cloning
Patient and Appointment implement Cloneable to demonstrate deep copy.

## 7. Enums
Used instead of String constants to prevent invalid states.

## 8. Streams
Used for analytics like:
- Average consultation fee
- Appointments per doctor

## 9. CSV Persistence
Implemented using try-with-resources and String.split().
