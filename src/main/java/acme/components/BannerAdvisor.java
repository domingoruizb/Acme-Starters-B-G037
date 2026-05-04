
package acme.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import acme.client.helpers.LoggerHelper;
import acme.entities.banners.Banner;

@ControllerAdvice
public class BannerAdvisor {

	@Autowired
	private BannerRepository repository;


	@ModelAttribute("banner")
	public Banner getBanner() {
		Banner result;

		try {
			result = this.repository.findRandomBanner();
		} catch (final Throwable err) {
			LoggerHelper.error(err.getLocalizedMessage());
			result = null;
		}

		return result;
	}
}
