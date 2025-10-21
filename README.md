<div align="center">
  <h3 align="center">KMDB Movies API</h3>
</div>

## About The Project

RESTful API for retrieving and managing movie data using Spring Boot

## Getting Started

### Prerequisites

* Java 17+
* Maven 3.6+

### Installation

1. Clone the repo
   ```
   git clone https://github.com/hugoqdesh/movies-api.git
   cd movies-api
   ```
1. Build the project with Maven
   ```
   mvn clean install
   ```
1. Run the Spring Boot application
   ```
   mvn spring-boot:run
   ```
   
### Usage

This section provides a detailed guide for interacting with the Movie Database API, including how to create, retrieve, update, delete, and filter data related to **Genres**, **Movies**, and **Actors**.

### Base URL
```
http://localhost:8080/api
```

### Movies

#### Create a new movie
```
POST /api/movies

{
  "title": "Inception",
  "releaseYear": 2010,
  "duration": 148,
  "genreIds": [1, 2],
  "actorIds": [1, 2, 3]
}
```

#### Get all movies (with pagination)

```
GET /api/movies?page=0&size=10
```

#### Get movie by id
```
GET /api/movies/{id}
```

#### Get all actors in a movie
```
GET /api/movies/{movieId}/actors
```

#### Filter movies

By Genre:
```
GET /api/movies?genre={genreId}
```

By Release Year:
```
GET /api/movies?year={releaseYear}
```

By Actor:
```
GET /api/movies?actor={actorId}
```

By Title:
```
GET /api/movies/search?title=matrix
```

#### Update a movie
```
PATCH /api/movies/{id}

{
  "title": "Inception: Director's Cut",
  "duration": 152
}
```

#### Delete a movie
```
DELETE /api/movies/{id}
```

### Actors

#### Create a new actor
```
POST /api/actors

{
  "name": "Leonardo DiCaprio",
  "birthDate": "1974-11-11",
  "movieIds": [1, 2]
}
```

#### Get all actors

```
GET /api/actors
```

Filter by name:
```
GET /api/actors?name=leo
```

#### Get actor by id
```
GET /api/actors/{id}
```

#### Get all movies for an actor
```
GET /api/actors/{actorId}/movies
```

#### Update a actor
```
PATCH /api/actors/{id}

{
  "name": "Leo DiCaprio"
}
```

#### Delete a actor
```
DELETE /api/actors/{id}
```

Force delete:
```
DELETE /api/actors/{id}?force=true
```

### Genres

#### Create a new genre
```
POST /api/genres

{
  "name": "Action"
}
```

#### Get all genres

```
GET /api/genres
```

#### Get genre by id
```
GET /api/genre/{id}
```

#### Get all movies in a genre
```
GET /api/genres/{genreId}/movies
```

#### Update a genre
```
PATCH /api/genres/{id}

{
  "name": "Action & Adventure"
}
```

#### Delete a movie
```
DELETE /api/genres/{id}
```

Force delete:
```
DELETE /api/genres/{id}?force=true
```