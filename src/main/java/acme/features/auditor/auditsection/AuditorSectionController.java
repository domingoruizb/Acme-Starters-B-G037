
package acme.features.auditor.auditsection;

import javax.annotation.PostConstruct;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import acme.client.controllers.AbstractController;
import acme.entities.audits.AuditSection;
import acme.realms.Auditor;

@Controller
public class AuditorSectionController extends AbstractController<Auditor, AuditSection> {

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.setMediaType(MediaType.TEXT_HTML);

		super.addBasicCommand("list", AuditorSectionListService.class);
		super.addBasicCommand("show", AuditorSectionShowService.class);
		super.addBasicCommand("create", AuditorSectionCreateService.class);
		super.addBasicCommand("update", AuditorSectionUpdateService.class);
		super.addBasicCommand("delete", AuditorSectionDeleteService.class);
	}

}
