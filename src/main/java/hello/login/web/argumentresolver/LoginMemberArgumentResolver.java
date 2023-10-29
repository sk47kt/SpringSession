package hello.login.web.argumentresolver;

import hello.login.domain.SessionConst;
import hello.login.domain.member.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");

        boolean hasParameterAnnotation = parameter.hasParameterAnnotation(Login.class); //Login 어노테이션이 parameter 정보에있는가
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());

        return hasParameterAnnotation && hasMemberType; // 두가지를 다 만족하는 true
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);
        if (session == null) {
            return null;
        }
        log.info("Arg {}",session.getAttribute(SessionConst.LOGIN_MEMBER));
        return session.getAttribute(SessionConst.LOGIN_MEMBER);
    }
}
