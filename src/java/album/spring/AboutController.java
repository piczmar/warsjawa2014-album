package album.spring;

import album.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AboutController {
	@Autowired
	protected VersionService versionService;

	@RequestMapping(value = "/about/index.dispatch", method = RequestMethod.GET)
	public ModelAndView getText() {

		ModelMap model = new ModelMap();
		model.addAttribute("appName", "Simple Album Demo");
		model.addAttribute("version", versionService.version());

		return new ModelAndView("/mvc/about/index", model);
	}
}
