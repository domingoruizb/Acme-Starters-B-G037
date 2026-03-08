
package acme.features.any.auditor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditReport;
import acme.realms.Auditor;

@Repository
public interface AnyAuditorRepository extends AbstractRepository {

	@Query("select r from AuditReport r where r.id = :reportId")
	AuditReport findAuditReportById(int reportId);

	@Query("select r.auditor from AuditReport r where r.id = :reportId")
	Auditor findAuditorByReportId(int reportId);

}
