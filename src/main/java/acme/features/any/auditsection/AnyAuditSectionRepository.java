
package acme.features.any.auditsection;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditReport;
import acme.entities.audits.AuditSection;

@Repository
public interface AnyAuditSectionRepository extends AbstractRepository {

	@Query("select r from AuditReport r where r.id = :reportId")
	AuditReport findAuditReportById(int reportId);

	@Query("select s from AuditSection s where s.id = :id")
	AuditSection findAuditSectionById(int id);

	@Query("select s from AuditSection s where s.auditReport.id = :reportId")
	Collection<AuditSection> findSectionsByReportId(int reportId);

}
