/**
 * 카테고리가 유효하지 않으면 true 반환
 * 조건: 공백일 경우
 * @param category
 * @returns {boolean}
 */
function inValidCategory(category) {

    return category === "";
}

/**
 * 작성자가 유효하지 않으면 true 반환
 * 조건: 공백이거나 3글자 이상 5글자 미만일 경우
 * @param writer
 * @returns {boolean}
 */
function inValidWriter(writer) {

    return writer === "" || writer.length < 3 || writer.length >= 5;
}

/**
 * 두 비밀번호가 일치하지 않으면 true 반환
 * @param password
 * @param confirmPassword
 * @returns {boolean}
 */
function passwordMismatch(password, confirmPassword) {

    return password !== confirmPassword;
}

/**
 * 비밀번호가 유효하지 않으면 true 반환
 * 조건: 영문, 숫자, 특수문자가 포함되어 있지 않거나 길이가 4글자 이상, 16글자 미만일 경우
 * @param password
 * @returns {boolean}
 */
function inValidPassword(password) {
    if (password.length < 4 || password.length >= 16) {
        return true;
    }

    // 영문, 숫자, 특수문자를 각각 한 글자 이상 포함하는지 확인
    let letterPattern = /[a-zA-Z]/;
    let numberPattern = /[0-9]/;
    let specialCharPattern = /[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/;

    // 하나라도 패턴과 맞지 않으면 true 반환
    return !(letterPattern.test(password) && numberPattern.test(password) && specialCharPattern.test(password));
}

/**
 * 제목이 유효하지 않을 경우 true 반환
 * 조건: 공백이거나 4글자 이상, 100글자 미만일 경우
 * @param title
 * @returns {boolean}
 */
function inValidTitle(title) {

    return title === "" || title.length() < 4 || title.length() >= 100;
}

/**
 * 내용이 유효하지 않을 경우 true 반환
 * 조건: 공백이거나 4글자 이상, 2000글자 미만일 경우
 * @param content
 * @returns {boolean}
 */
function inValidContent(content) {

    return content === "" || content.length() < 4 || content.length() >= 2000;
}