INTRODUCTION
====================

This is an implementation of a model for the game of Yahtzee.

This project contains no 'view'.

Reading the unit tests will elucidate the role of each class.

This project is meant as a code specimen for recruiters. It is meant to demonstrate my skill in clean coding, object oriented design, and test-driven development.

This project recieved a grade of 19/20 in the Software Engineering Practice module within the MSc Computer Science at the The University of St Andrews.

TECHNOLOGIES
====================

- Java
- JUnit
- Mockito

STRUCTURE
====================

```
yahtzee
├── planning                    // Requirements, class definitions, and rough plan 
├── src                        
│   ├── main...
│   │   ├── implementation      // Setup, the central 'game loop', event listeners
│   │   │   ├── scoringoptions  // Calculate score for any scoring option given die faces
│   │   │   └── ...             // All core functionality classes 
│   │   └── ...                 // Interfaces for each class
│   └── test...                 // Unit tests
├── build.gradle                // Config to manage external dependencies
└── ... 
```
