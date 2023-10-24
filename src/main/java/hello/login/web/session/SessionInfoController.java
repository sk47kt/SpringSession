package hello.login.web.session;

import jdk.jshell.spi.ExecutionControl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션없음";
        }

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session msg={} , value={}", name, session.getAttribute(name)));

        log.info("sessionId={}", session.getId()); // 세션값
        log.info("session.getMaxInactiveInterval() = " + session.getMaxInactiveInterval()); // 세션유효시간
        log.info("new Date(session.getCreationTime()) = " + new Date(session.getCreationTime())); // 세션 생성일시
        log.info("LastAccessedTime={}",new Date(session.getLastAccessedTime())); // 세션 사용자가 서버에 접근한 시간
        log.info("isNew={}", session.isNew()); // 새로 생성된 세션 인지 과거에 생성된 세션인지
        return "세션출력";
    }
}
