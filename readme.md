# Resume Matcher Microservice

A scalable, AI-powered microservices system that parses resumes and automatically matches them with the most relevant job descriptions.

---

## Project Overview

This system enables HR teams to upload multiple resumes, parse them for skills, and intelligently match them against available job descriptions using AI-based matching — all in an event-driven, cloud-native architecture.

---

## Tech Stack and Why

| Technology | Why it's used |
|------------|---------------|
| **Java 17 + Spring Boot** | Robust backend framework for REST APIs and service layering |
| **Apache Kafka** | Enables asynchronous, decoupled communication between microservices |
| **MongoDB** | NoSQL database ideal for storing flexible, nested documents like job descriptions and match results |
| **AWS S3** | Cloud storage for resume files, ensuring scalability and durability |
| **Apache Tika** | Used for parsing resume content from PDF/Word files |
| **Caffeine Cache** | In-memory caching of known JD skills to improve performance |
| **Docker** | Containerizes each microservice to ensure consistent deployment |
| **Maven** | Dependency and project lifecycle management for Java projects |

---

## System Architecture

```plaintext
                 +-------------+
                 |  HR Portal  |
                 +------+------+
                        |
               Upload resumes (PDFs)
                        |
                 +------v------+
                 | Upload Service |
                 +---------------+
                        |
              [Uploads to AWS S3]
                        |
              [Publishes Kafka Event]
                        |
                 +------v-------+
                 | Parser Service |
                 +---------------+
                        |
         [Consumes event, parses resume]
                        |
         [Compares skills with JDs from MongoDB]
                        |
         [Saves matched job + score in MongoDB]
                        |
                 +------v------+
                 | Status API  |
                 +-------------+
