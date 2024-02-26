# Data Structures

## Overview
The Data Structures project provides implementations of essential data structures, including ArrayList, LinkedList, and HashMap, in both Java and Python. This project is designed for educational purposes, allowing developers to learn about the inner workings of these data structures and how they are implemented.

## Implemented Data Structures
- **ArrayList**: A dynamic array implementation that automatically resizes itself as needed.
- **LinkedList**: A linked list implementation that allows for efficient insertion, deletion, and traversal.
- **HashMap**: A hash table implementation that provides fast lookup and insertion times based on key-value pairs.

## Technologies Used
- **Java**: Core programming language for implementing the data structures.
- **Python**: Core programming language for implementing the data structures in a different paradigm.

## Challenges Faced
### Resolving Hash Collisions in HashMap
One significant challenge encountered during the development of the HashMap implementation in both Java and Python was resolving hash collisions. To address this issue, I implemented a solution using separate chaining. In Java, I created a generic `Pair` class to encapsulate key-value pairs and stored a linked list of pairs at each hash index to handle collisions effectively. This approach ensured efficient storage and retrieval of key-value pairs, even in the presence of hash collisions. In python, a similar challenge was faced, but I used a standard list instead of a linkedlist, and tuples to store the key-value pairs.

### Making Classes Generic in Java
Another challenge was making the classes generic in Java. Due to Java's strong typing system, I had to devise a method to work with generic types effectively. I learned that Java does not allow direct instantiation of generic types or arrays of generic types. To overcome this limitation, I instantiated arrays of objects and cast them to the generic type, ensuring type safety and flexibility in my implementations.

### Dynamically Typed Nature of Python
In Python, the dynamically typed nature of the language presented challenges in managing variable assignments and references effectively. It was crucial to maintain clarity and coherence in the codebase while dealing with dynamically typed variables. Through careful planning and rigorous testing, I successfully navigated these challenges, ensuring the robustness and reliability of the Python implementations.

## Installation and Usage
This project is primarily for educational purposes, and it is not recommended for practical use. To explore the implementations of these data structures:
- Clone the repository to your local machine.
- Navigate to the `java` or `python` directory based on the language you want to use.
- Compile (Java) or run tests (Python) as applicable.
- Import the desired data structure classes into your own projects and use them for learning purposes.

## Contributing
This project is not open to contributions. It was created for personal learning purposes, and contributions are not recommended.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
