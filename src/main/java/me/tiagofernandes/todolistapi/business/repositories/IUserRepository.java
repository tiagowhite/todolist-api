package me.tiagofernandes.todolistapi.business.repositories;

import me.tiagofernandes.todolistapi.models.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserRepository extends MongoRepository<User, String> {
}
