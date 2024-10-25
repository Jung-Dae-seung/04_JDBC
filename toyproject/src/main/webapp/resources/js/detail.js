function confirmDelete(stdNo) {
    document.getElementById('stdNo').value = stdNo;
    new bootstrap.Modal(document.getElementById('deleteModal')).show();
}