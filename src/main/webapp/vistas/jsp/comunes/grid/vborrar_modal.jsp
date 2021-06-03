
<!-- Delete Modal HTML -->
<div id="borrarModal" class="modal fade">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- give your form an action and method -->
			<form action="del" method="POST">
				<div class="modal-header">
					<h4 class="modal-title">Eliminar el Registro</h4>
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
				</div>
				<div class="modal-body">
					<p>¿Esta seguro que desea borrarlo?</p>
					<p class="text-warning">
						<small>Esta acción no se podrá deshacer.</small>
					</p>
				</div>
				<div class="modal-footer">
					<!-- add a hidden input field to store ID for next step -->
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" /> <input type="hidden" name="regId"
						id="regId" value="" /> <input type="button"
						class="btn btn-default" data-dismiss="modal" value="Cancelar">
					<button class="btn btn-danger" type="submit">Borrar</button>
				</div>
			</form>
		</div>
	</div>
</div>
<script>
$(function () {
    $('a.delete').click(function (e) {
        e.preventDefault();
        var link = this;
        var deleteModal = $("#borrarModal");
        // obtenemos el Id del registro a borrar
        deleteModal.find('input[name=regId]').val(link.dataset.id);
        // abrimos la ventana modal
        deleteModal.modal();
    });
});
</script>