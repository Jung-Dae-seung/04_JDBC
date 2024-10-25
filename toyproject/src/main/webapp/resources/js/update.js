const stdScore = document.getElementById('stdScore');
const stdAge = document.getElementById('stdAge');

document.getElementById('studentForm').addEventListener('submit', function(e) {
    if (stdScore.value === "") {
        e.preventDefault();
        alert('유효한 성적을 선택하세요');
		stdScore.focus();
    }
	
    if (stdAge.value < 1 || stdAge.value > 120) {
        e.preventDefault();
        alert('유효한 나이를 입력해주세요 (1-120)');
		stdAge.focus();
    }
});