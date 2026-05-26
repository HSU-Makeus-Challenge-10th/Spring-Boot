const webauthnJSON = window.webauthnJSON;

const usernameInput = document.getElementById('username');
const displayNameInput = document.getElementById('displayName');
const registerOptionsUrl = document.getElementById('registerOptionsUrl');
const registerVerifyUrl = document.getElementById('registerVerifyUrl');
const authOptionsUrl = document.getElementById('authOptionsUrl');
const authVerifyUrl = document.getElementById('authVerifyUrl');
const logOutput = document.getElementById('logOutput');

function log(msg, data = null) {
    console.log(msg, data);
    const time = new Date().toLocaleTimeString();
    let logText = `[${time}] ${msg}`;
    if (data) {
        logText += `\n${JSON.stringify(data, null, 2)}`;
    }
    logOutput.textContent = logText + '\n\n' + logOutput.textContent;
}

async function authFetch(url, options) {
    options.headers = {
        'Content-Type': 'application/json',
        ...options.headers
    };
    const response = await fetch(url, options);
    if (!response.ok) {
        throw new Error(`HTTP 요청 에러 발생 (상태 코드: ${response.status})`);
    }
    const text = await response.text();
    return text ? JSON.parse(text) : {};
}

document.getElementById('registerBtn').addEventListener('click', async () => {
    log("==== 🔐 패스키 등록 프로세스 시작 ====");
    try {
        const username = usernameInput.value;
        const displayName = displayNameInput.value;

        log("1. 서버에 등록 옵션(Challenge) 요청 중...");
        const optionsRes = await authFetch(registerOptionsUrl.value, {
            method: "POST",
            body: JSON.stringify({ username, displayName })
        });

        log("-> 서버 응답:", optionsRes);

        const serverOptions = optionsRes.publicKey || optionsRes;

        log("2. 스마트폰/노트북 생체 인증창 호출 중 (credentials.create)...");
        const credential = await webauthnJSON.create({
            publicKey: serverOptions
        });

        log("-> 지문 인증 완료! 생성된 패스키 정보:", credential);

        log("3. 서버에 최종 등록 검증 요청 중...");
        const verifyRes = await authFetch(registerVerifyUrl.value, {
            method: "POST",
            body: JSON.stringify(credential) // 서버가 요구하는 JSON 형태로 전송
        });

        log("==== 🎉 패스키 등록 완료 성공 ====", verifyRes);
        alert("패스키가 성공적으로 등록되었습니다!");

    } catch (error) {
        log("❌ 패스키 등록 실패", error.message);
        alert("등록 중 에러가 발생했습니다. 로그 창을 확인해주세요.");
    }
});

document.getElementById('loginBtn').addEventListener('click', async () => {
    log("==== 🔓 패스키 로그인 프로세스 시작 ====");
    try {
        const username = usernameInput.value;

        log("1. 서버에 로그인(인증) 옵션 요청 중...");
        const optionsRes = await authFetch(authOptionsUrl.value, {
            method: "POST",
            body: JSON.stringify({ username })
        });

        log("-> 서버 응답:", optionsRes);

        const serverOptions = optionsRes.publicKey || optionsRes;

        log("2. 스마트폰/노트북 생체 인증창 호출 중 (credentials.get)...");
        const credential = await webauthnJSON.get({
            publicKey: serverOptions
        });

        log("-> 인증 완료! 서명된 데이터:", credential);

        log("3. 서버에 최종 로그인 검증 요청 중...");
        const verifyRes = await authFetch(authVerifyUrl.value, {
            method: "POST",
            body: JSON.stringify(credential)
        });

        log("==== 🎉 패스키 로그인 완료 성공 ====", verifyRes);
        alert("패스키 로그인이 성공했습니다!");

    } catch (error) {
        log("❌ 패스키 로그인 실패", error.message);
        alert("로그인 중 에러가 발생했습니다. 로그 창을 확인해주세요.");
    }
});