
package acme.features.inventor.part;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;

@Repository
public interface InventorPartRepository extends AbstractRepository {

	@Query("SELECT s FROM Invention s WHERE s.id = :inventionId")
	Invention findInventionById(int inventionId);

	@Query("SELECT p FROM Part p WHERE p.id = :id")
	Part findPartById(int id);

	@Query("SELECT p FROM Part p WHERE p.invention.id = :inventionId")
	Collection<Part> findPartsByInventionId(int inventionId);

}
