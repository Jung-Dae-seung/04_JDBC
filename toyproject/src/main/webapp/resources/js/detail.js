function confirmDelete(stdNo) {
    document.getElementById('deleteStdNo').value = stdNo;
    new bootstrap.Modal(document.getElementById('deleteModal')).show();
}