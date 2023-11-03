// Función para obtener y guardar el valor del contador en el almacenamiento local
  function getAndSetContador() {
    fetch('/compra?opcion=getContador')
      .then(response => response.text())
      .then(contador => {
        // Guardar el valor del contador en el localStorage
        localStorage.setItem('contador', contador);
        // Actualizar el contador en la página
        document.getElementById('contador').innerText = contador;
      });
  }