# Manual de Usuario - HomeStore

## 1. Introducción

HomeStore es un sistema web orientado a la gestión de productos, inventario y compras en línea.

El sistema permite que los visitantes se registren, inicien sesión, consulten los productos disponibles, agreguen artículos al carrito y completen el proceso de compra.

También incluye funcionalidades administrativas para registrar productos, modificar precios, actualizar existencias y revisar el estado del inventario.

---

## 2. Acceso al sistema

Para ingresar al sistema:

1. Abra un navegador web moderno, como Google Chrome, Microsoft Edge o Mozilla Firefox.
2. Ingrese la dirección asignada al sistema HomeStore.
3. Espere a que se cargue la página principal.

> **Captura sugerida:** Página principal de HomeStore.

---

## 3. Registro de usuario

Para crear una cuenta:

1. Ingrese a la página principal.
2. Presione el botón **“Regístrese”**.
3. Complete los siguientes campos:
   - Nombre.
   - Correo electrónico.
   - Contraseña.
4. Verifique que el correo electrónico no haya sido registrado anteriormente.
5. Presione el botón **“Crear cuenta”**.
6. El sistema mostrará un mensaje indicando que el registro se realizó correctamente.
7. Regrese al inicio e ingrese con las credenciales creadas.

### Recomendaciones

- El correo debe tener un formato válido.
- La contraseña debe cumplir con la longitud mínima establecida.
- No comparta su contraseña con otras personas.

> **Captura sugerida:** Formulario de registro.

---

## 4. Inicio de sesión

Para iniciar sesión:

1. Ingrese a la página principal.
2. Escriba su correo electrónico.
3. Escriba su contraseña.
4. Presione **“Inicio de Sesión”**.
5. Si los datos son correctos, el sistema lo dirigirá al módulo de productos.
6. Si los datos son incorrectos, se mostrará un mensaje de error.

> **Captura sugerida:** Formulario de inicio de sesión.

---

## 5. Cerrar sesión

Para cerrar la sesión:

1. Presione la opción **“Cerrar sesión”**.
2. El sistema finalizará la sesión activa.
3. Posteriormente será redirigido a la página principal.

Cerrar la sesión evita que otra persona utilice la cuenta sin autorización.

---

## 6. Consultar productos

Para visualizar los productos:

1. Inicie sesión.
2. Seleccione la opción **“Productos”**.
3. El sistema mostrará una tabla con:
   - Código.
   - Nombre.
   - Precio.
   - Costo.
   - Cantidad disponible.
   - Acciones disponibles.

Los productos con existencias pueden agregarse al carrito.

> **Captura sugerida:** Listado de productos.

---

## 7. Agregar productos al carrito

Para agregar un producto:

1. Ingrese al módulo **“Productos”**.
2. Localice el artículo que desea comprar.
3. Presione el botón **“Agregar”**.
4. El producto será añadido al carrito.
5. Ingrese a la opción **“Carrito”** para revisar la selección.

Si el producto está agotado, el botón para agregarlo permanecerá deshabilitado.

---

## 8. Gestionar el carrito

En el carrito puede:

- Consultar los productos seleccionados.
- Visualizar el precio unitario.
- Revisar la cantidad.
- Consultar el subtotal.
- Aumentar la cantidad.
- Disminuir la cantidad.
- Eliminar un producto.
- Consultar el monto total de la compra.

El sistema verifica que la cantidad solicitada no supere el stock disponible.

> **Captura sugerida:** Carrito de compras.

---

## 9. Continuar al pago

Para continuar con la compra:

1. Revise los productos del carrito.
2. Verifique las cantidades y el monto total.
3. Presione **“Continuar al pago”**.
4. El sistema abrirá la pantalla de checkout.

No se puede continuar al pago si el carrito está vacío.

---

## 10. Completar el checkout

En la pantalla de checkout debe seleccionar:

1. Un método de pago.
2. Un método de envío.
3. Una sucursal.
4. La información adicional que solicite el formulario.

El sistema mostrará:

- Subtotal.
- Costo del envío.
- Total final.

Revise toda la información antes de confirmar.

> **Captura sugerida:** Pantalla de checkout.

---

## 11. Confirmar una compra

Para confirmar la compra:

1. Revise los datos del checkout.
2. Verifique el total final.
3. Presione **“Confirmar compra”**.
4. El sistema registrará la venta.
5. Se almacenarán los detalles de los productos.
6. El stock se reducirá según las cantidades compradas.
7. El carrito quedará marcado como completado.
8. Se mostrará una pantalla de compra exitosa.
9. La confirmación incluirá el número de factura.

> **Captura sugerida:** Pantalla de compra exitosa.

---

## 12. Gestión de productos

Los usuarios autorizados pueden administrar productos.

### Registrar un producto

1. Ingrese a **“Productos”**.
2. Presione **“Nuevo Producto”**.
3. Complete:
   - Nombre.
   - Descripción.
   - Categoría.
   - Proveedor.
   - Precio de venta.
   - Precio de costo.
   - Cantidad disponible.
4. Presione **“Guardar”**.

### Editar un producto

1. Localice el producto.
2. Presione el botón de edición.
3. Modifique los datos necesarios.
4. Guarde los cambios.

### Eliminar un producto

1. Localice el producto.
2. Presione el botón de eliminar.
3. Confirme la operación cuando corresponda.

> **Captura sugerida:** Formulario de producto.

---

## 13. Gestión de inventario

Para revisar el inventario:

1. Inicie sesión.
2. Seleccione la opción **“Inventario”**.
3. El sistema mostrará los productos clasificados según sus existencias.

### Estados del inventario

- **Disponible:** más de 10 unidades.
- **Poco stock:** entre 1 y 10 unidades.
- **Agotado:** 0 unidades.

La pantalla también muestra un resumen con la cantidad de productos en cada estado.

Desde el inventario se puede acceder a la edición del producto para modificar:

- Precio.
- Costo.
- Stock.

> **Captura sugerida:** Panel de inventario.

---

## 14. Cambio de idioma

En la barra superior se encuentran las opciones:

- **ES:** español.
- **EN:** inglés.

Presione la opción deseada para cambiar el idioma disponible en la interfaz.

---

## 15. Problemas frecuentes

### No puedo iniciar sesión

Compruebe:

- Que el correo esté escrito correctamente.
- Que la contraseña sea correcta.
- Que la cuenta haya sido registrada.

### No aparecen categorías o proveedores

Puede deberse a que no existen registros en la base de datos. El administrador debe registrar las categorías y proveedores necesarios.

### No puedo agregar un producto

Revise si el producto está agotado o si la cantidad solicitada supera el stock disponible.

### No aparecen métodos de pago, envío o sucursales

El administrador debe verificar que existan métodos activos y sucursales registradas en la base de datos.

### La página me devuelve al inicio de sesión

La sesión puede haber finalizado o la ruta solicitada requiere autenticación. Inicie sesión nuevamente.

---

## 16. Soporte

Para solicitar ayuda puede utilizar los medios de contacto definidos por el equipo de HomeStore.

- Correo: soporte@homestore.com
- Teléfono: +506 8888-9999

> Los datos de contacto deben sustituirse por la información oficial del proyecto antes de la entrega final.