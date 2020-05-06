package com.iu.s5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.iu.s5.board.BoardVO;
import com.iu.s5.notice.NoticeService;
import com.iu.s5.util.Pager;

@Controller
@RequestMapping("json")
public class JsonController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("json1")
	@ResponseBody
	public List<BoardVO> json1(Pager pager) throws Exception {
		// ModelAndView mv = new ModelAndView();
		BoardVO boardVO = noticeService.boardSelect(30);
		List<BoardVO> ar = noticeService.boardList(pager);

		// mv.addObject("result", "{\"name\" : \"iu\"}");
		// mv.addObject("result", json);
		// mv.setViewName("common/ajaxResult");
		return ar;
	}

}
