//package kr.co.spring_project.common.auth;
//
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//
//public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver{
//	// 어떤 파라미터에 대해 이 Resolver가 동작할지 결정
//	// ex) @LoginUser + Long이면 내가 처리
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        boolean hasAnnotation = parameter.hasParameterAnnotation(LoginUser.class);
//        boolean isLongType = Long.class.isAssignableFrom(parameter.getParameterType());
//        return hasAnnotation && isLongType;
//    }
//
//    // 실제로 파라미터 값을 만들어서 반환 (여기서는 세션에서 userId 꺼냄)
//    // 실제 값은 뭘로 넣어줄래? 결정
//    // ex) 세션에서 LOGIN_USER 꺼내서 넣기
//    @Override
//    public Object resolveArgument(
//            MethodParameter parameter,
//            ModelAndViewContainer mavContainer,
//            NativeWebRequest webRequest,
//            WebDataBinderFactory binderFactory
//    ) {
//        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
//        HttpSession session = request.getSession(false); // false: 세션 없으면 새로 만들지 않음
//
//        if (session == null) {
//            throw new IllegalStateException("로그인이 필요합니다. (세션이 없습니다)");
//        }
//
//        Object userIdObj = session.getAttribute("LOGIN_USER");
//
//        if (userIdObj == null) {
//            throw new IllegalStateException("로그인이 필요합니다. (세션에 로그인 정보가 없습니다)");
//        }
//
//        // 세션에 Long으로 저장했다고 가정
//        return (Long) userIdObj;
//    }
//}
