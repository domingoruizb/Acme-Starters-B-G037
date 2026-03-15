
package acme.features.any.tactic;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.strategy.Strategy;
import acme.entities.strategy.Tactic;

@Repository
public interface AnyTacticRepository extends AbstractRepository {

	@Query("select t from Tactic t where t.strategy.id = :strategyId and t.strategy.draftMode = false")
	Collection<Tactic> findTacticByStrategyId(int strategyId);

	@Query("select t from Tactic t where t.id = :id")
	Tactic findTacticById(int id);

	@Query("select s from Strategy s where s.id = :id")
	Strategy findStrategyById(int id);

}
