document.getElementById('studentForm').addEventListener('submit', function(e) {
    const stdAge = document.getElementById('stdAge').value;
    if (stdAge < 1 || stdAge > 120) {
        e.preventDefault();
        alert('유효한 나이를 입력해주세요 (1-120)');
    }
});