package Logico;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ConexionBD {
	private ArrayList<Cliente>LosClientes;
	public ConexionBD() {
		LosClientes = new ArrayList<>(); 

	}

	public void setLosClientes(ArrayList<Cliente> clientes) {
		this.LosClientes = clientes;
	}

	public static void main(String[] args) {
		String user = "sa";
		String password = "sa123";
		String connectionUrl = "jdbc:sqlserver://localhost\\MSSQLSERVER01;" +
				"databaseName=TiendaComponentes;" +
				"user=" + user + ";" +
				"password=" + password + ";" +
				"encrypt=false;" +
				"trustServerCertificate=true;";

		try (Connection connection = DriverManager.getConnection(connectionUrl)) {
			System.out.println("Conexión exitosa a la base de datos.");


		} catch (SQLException e) {
			System.out.println("Error al conectar con la base de datos: " + e.getMessage());
		}
	}
	private void setParametrosCliente(PreparedStatement pstmt, Cliente cliente) throws SQLException {
		pstmt.setString(1, cliente.getIdCliente());
		pstmt.setString(2, cliente.getNombre());
		pstmt.setString(3, cliente.getDireccion());
		pstmt.setString(4, cliente.getTelefono());
		pstmt.setString(5, cliente.getCedula());

		if (cliente instanceof Empleado) {
			Empleado e = (Empleado) cliente;
			pstmt.setString(6, "Empleado");
			pstmt.setBigDecimal(7, new BigDecimal(e.getSueldoTotal()));
			pstmt.setInt(8, e.getCantAniosTrabajando());
			pstmt.setInt(9, e.getDescuento());
			pstmt.setNull(10, Types.INTEGER);
		} else if (cliente instanceof Visitante) {
			Visitante v = (Visitante) cliente;
			pstmt.setString(6, "Cliente");
			pstmt.setNull(7, Types.DECIMAL);
			pstmt.setNull(8, Types.INTEGER);
			pstmt.setNull(9, Types.INTEGER);
			pstmt.setInt(10, v.getCantCompras());
		} else {
			pstmt.setString(6, "Cliente");
			pstmt.setNull(7, Types.DECIMAL);
			pstmt.setNull(8, Types.INTEGER);
			pstmt.setNull(9, Types.INTEGER);
			pstmt.setNull(10, Types.INTEGER);
		}
	}

	public void insertarCliente(Connection conn, Cliente cliente) throws SQLException {
		String sql = "INSERT INTO cliente (id_cliente, nombre, direccion, telefono, cedula, tipo_cliente, sueldo_total, cant_anios_trabajando, descuento, cant_compras) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		setParametrosCliente(pstmt, cliente);
		pstmt.executeUpdate();
		System.out.println("Cliente insertado: " + cliente.getNombre());
	}

	public void actualizarCliente(Connection conn, Cliente cliente) throws SQLException {
		String sql = "UPDATE cliente SET nombre = ?, direccion = ?, telefono = ?, cedula = ?, tipo_cliente = ?, sueldo_total = ?, cant_anios_trabajando = ?, descuento = ?, cant_compras = ? WHERE id_cliente = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, cliente.getNombre());
		pstmt.setString(2, cliente.getDireccion());
		pstmt.setString(3, cliente.getTelefono());
		pstmt.setString(4, cliente.getCedula());

		if (cliente instanceof Empleado) {
			Empleado e = (Empleado) cliente;
			pstmt.setString(5, "Empleado");
			pstmt.setBigDecimal(6, new BigDecimal(e.getSueldoTotal()));
			pstmt.setInt(7, e.getCantAniosTrabajando());
			pstmt.setInt(8, e.getDescuento());
			pstmt.setNull(9, Types.INTEGER);
		} else if (cliente instanceof Visitante) {
			Visitante v = (Visitante) cliente;
			pstmt.setString(5, "Cliente");
			pstmt.setNull(6, Types.DECIMAL);
			pstmt.setNull(7, Types.INTEGER);
			pstmt.setNull(8, Types.INTEGER);
			pstmt.setInt(9, v.getCantCompras());
		} else {
			pstmt.setString(5, "Cliente");
			pstmt.setNull(6, Types.DECIMAL);
			pstmt.setNull(7, Types.INTEGER);
			pstmt.setNull(8, Types.INTEGER);
			pstmt.setNull(9, Types.INTEGER);
		}

		pstmt.setString(10, cliente.getIdCliente());
		pstmt.executeUpdate();
		System.out.println("Cliente actualizado: " + cliente.getNombre());
	}

	public void eliminarCliente(Connection conn, String idCliente) throws SQLException {
		String sql = "DELETE FROM cliente WHERE id_cliente = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, idCliente);
		int filasAfectadas = pstmt.executeUpdate();
		System.out.println("Cliente eliminado. Filas afectadas: " + filasAfectadas);
	}

	// Método para verificar si un cliente existe en la BD
	public boolean existeCliente(Connection conn, String idCliente) throws SQLException {
		String sql = "SELECT COUNT(*) FROM cliente WHERE id_cliente = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, idCliente);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return rs.getInt(1) > 0;
	}

	private void setParametrosComponente(PreparedStatement pstmt, Componente componente) throws SQLException {
		pstmt.setString(1, componente.getIdComponente());
		pstmt.setString(2, componente.getNumeroSerie());
		pstmt.setBigDecimal(3, new BigDecimal(componente.getPrecio()));
		pstmt.setInt(4, componente.getCantidadDisponible());
		pstmt.setString(5, componente.getMarca());
		pstmt.setString(6, componente.getModelo());

		// Campos específicos según el tipo de componente
		if (componente instanceof MicroProcesador) {
			MicroProcesador mp = (MicroProcesador) componente;
			pstmt.setString(7, "MicroProcesador");
			pstmt.setString(8, mp.getTipoConex());
			pstmt.setBigDecimal(9, new BigDecimal(mp.getVelProcesamientoGHz()));
			pstmt.setNull(10, Types.DECIMAL);
			pstmt.setNull(11, Types.VARCHAR);
			pstmt.setNull(12, Types.VARCHAR);
			pstmt.setNull(13, Types.VARCHAR);
			pstmt.setNull(14, Types.VARCHAR);
		} else if (componente instanceof RAM) {
			RAM ram = (RAM) componente;
			pstmt.setString(7, "RAM");
			pstmt.setNull(8, Types.VARCHAR);
			pstmt.setNull(9, Types.DECIMAL);
			pstmt.setBigDecimal(10, new BigDecimal(ram.getCantGB()));
			pstmt.setString(11, ram.getTipoMemoria());
			pstmt.setNull(12, Types.VARCHAR);
			pstmt.setNull(13, Types.VARCHAR);
			pstmt.setNull(14, Types.VARCHAR);
		} else if (componente instanceof DiscoDuro) {
			DiscoDuro dd = (DiscoDuro) componente;
			pstmt.setString(7, "DiscoDuro");
			pstmt.setString(8, dd.getTipoConex());
			pstmt.setNull(9, Types.DECIMAL);
			pstmt.setBigDecimal(10, new BigDecimal(dd.getCapAlmacenamiento()));
			pstmt.setNull(11, Types.VARCHAR);
			pstmt.setNull(12, Types.VARCHAR);
			pstmt.setNull(13, Types.VARCHAR);
			pstmt.setNull(14, Types.VARCHAR);
		} else if (componente instanceof TarjetaMadre) {
			TarjetaMadre tm = (TarjetaMadre) componente;
			pstmt.setString(7, "TarjetaMadre");
			pstmt.setNull(8, Types.VARCHAR);
			pstmt.setNull(9, Types.DECIMAL);
			pstmt.setNull(10, Types.DECIMAL);
			pstmt.setNull(11, Types.VARCHAR);
			pstmt.setString(12, tm.getConector());
			pstmt.setString(13, tm.getTipoRam());

			String conexionesStr = "";
			if (tm.getConexiones() != null && !tm.getConexiones().isEmpty()) {
				conexionesStr = String.join(",", tm.getConexiones());
			}
			pstmt.setString(14, conexionesStr);
		}
	}

	public void insertarComponente(Connection conn, Componente componente) throws SQLException {
		String sql = "INSERT INTO componente (id_componente, numero_serie, precio, cantidad_disponible, marca, modelo, tipo_componente, tipo_conexion, vel_procesamiento_ghz, cant_gb, tipo_memoria, conector, tipo_ram, conexiones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		setParametrosComponente(pstmt, componente);
		pstmt.executeUpdate();
		System.out.println("Componente insertado: " + componente.getMarca() + " " + componente.getModelo());
	}

	public void actualizarComponente(Connection conn, Componente componente) throws SQLException {
		String sql = "UPDATE componente SET numero_serie = ?, precio = ?, cantidad_disponible = ?, marca = ?, modelo = ?, tipo_componente = ?, tipo_conexion = ?, vel_procesamiento_ghz = ?, cant_gb = ?, tipo_memoria = ?, conector = ?, tipo_ram = ?, conexiones = ? WHERE id_componente = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, componente.getNumeroSerie());
		pstmt.setBigDecimal(2, new BigDecimal(componente.getPrecio()));
		pstmt.setInt(3, componente.getCantidadDisponible());
		pstmt.setString(4, componente.getMarca());
		pstmt.setString(5, componente.getModelo());

		// Campos específicos según el tipo de componente
		if (componente instanceof MicroProcesador) {
			MicroProcesador mp = (MicroProcesador) componente;
			pstmt.setString(6, "MicroProcesador");
			pstmt.setString(7, mp.getTipoConex());
			pstmt.setBigDecimal(8, new BigDecimal(mp.getVelProcesamientoGHz()));
			pstmt.setNull(9, Types.DECIMAL);
			pstmt.setNull(10, Types.VARCHAR);
			pstmt.setNull(11, Types.VARCHAR);
			pstmt.setNull(12, Types.VARCHAR);
			pstmt.setNull(13, Types.VARCHAR);
		} else if (componente instanceof RAM) {
			RAM ram = (RAM) componente;
			pstmt.setString(6, "RAM");
			pstmt.setNull(7, Types.VARCHAR);
			pstmt.setNull(8, Types.DECIMAL);
			pstmt.setBigDecimal(9, new BigDecimal(ram.getCantGB()));
			pstmt.setString(10, ram.getTipoMemoria());
			pstmt.setNull(11, Types.VARCHAR);
			pstmt.setNull(12, Types.VARCHAR);
			pstmt.setNull(13, Types.VARCHAR);
		} else if (componente instanceof DiscoDuro) {
			DiscoDuro dd = (DiscoDuro) componente;
			pstmt.setString(6, "DiscoDuro");
			pstmt.setString(7, dd.getTipoConex());
			pstmt.setNull(8, Types.DECIMAL);
			pstmt.setBigDecimal(9, new BigDecimal(dd.getCapAlmacenamiento()));
			pstmt.setNull(10, Types.VARCHAR);
			pstmt.setNull(11, Types.VARCHAR);
			pstmt.setNull(12, Types.VARCHAR);
			pstmt.setNull(13, Types.VARCHAR);
		} else if (componente instanceof TarjetaMadre) {
			TarjetaMadre tm = (TarjetaMadre) componente;
			pstmt.setString(6, "TarjetaMadre");
			pstmt.setNull(7, Types.VARCHAR);
			pstmt.setNull(8, Types.DECIMAL);
			pstmt.setNull(9, Types.DECIMAL);
			pstmt.setNull(10, Types.VARCHAR);
			pstmt.setString(11, tm.getConector());
			pstmt.setString(12, tm.getTipoRam());

			String conexionesStr = "";
			if (tm.getConexiones() != null && !tm.getConexiones().isEmpty()) {
				conexionesStr = String.join(",", tm.getConexiones());
			}
			pstmt.setString(13, conexionesStr);
		}

		pstmt.setString(14, componente.getIdComponente());
		pstmt.executeUpdate();
		System.out.println("Componente actualizado: " + componente.getMarca() + " " + componente.getModelo());
	}

	// Método específico para eliminar un componente
	public void eliminarComponente(Connection conn, String idComponente) throws SQLException {
		String sql = "DELETE FROM componente WHERE id_componente = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, idComponente);
		int filasAfectadas = pstmt.executeUpdate();
		System.out.println("Componente eliminado. Filas afectadas: " + filasAfectadas);
	}

	// Método para verificar si un componente existe en la BD
	public boolean existeComponente(Connection conn, String idComponente) throws SQLException {
		String sql = "SELECT COUNT(*) FROM componente WHERE id_componente = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, idComponente);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return rs.getInt(1) > 0;
	}
	public void insertarFactura(Connection conn, Factura factura) throws SQLException {
	    // 1. Insertar la factura principal
	    String sqlFactura = "INSERT INTO Factura (id_factura, id_cliente, costo_total) VALUES (?, ?, ?)";
	    PreparedStatement pstmtFactura = conn.prepareStatement(sqlFactura);

	    pstmtFactura.setString(1, factura.getIdFactura());
	    pstmtFactura.setString(2, factura.getCliente().getIdCliente());
	    pstmtFactura.setDouble(3, factura.getCostoTotal());

	    pstmtFactura.executeUpdate();
	    System.out.println("Factura insertada: " + factura.getIdFactura());

	    // 2. Insertar los componentes de la factura en detalle_factura
	    if (factura.getLosComponentes() != null && !factura.getLosComponentes().isEmpty()) {
	        // CORREGIDO: Incluir la columna cantidad
	        String sqlComponentes = "INSERT INTO detalle_factura (id_factura, id_componente, cantidad) VALUES (?, ?, ?)";
	        PreparedStatement pstmtComponentes = conn.prepareStatement(sqlComponentes);

	        // Contar las cantidades de cada componente
	        HashMap<String, Integer> componenteCantidades = new HashMap<>();
	        
	        // Contar cuántas veces aparece cada componente
	        for (Componente componente : factura.getLosComponentes()) {
	            String idComponente = componente.getIdComponente();
	            componenteCantidades.put(idComponente, 
	                    componenteCantidades.getOrDefault(idComponente, 0) + 1);
	        }

	        // Insertar cada componente único con su cantidad total
	        for (Map.Entry<String, Integer> entry : componenteCantidades.entrySet()) {
	            pstmtComponentes.setString(1, factura.getIdFactura());
	            pstmtComponentes.setString(2, entry.getKey());
	            pstmtComponentes.setInt(3, entry.getValue()); // ESTE ES EL PARÁMETRO 3 QUE FALTABA
	            pstmtComponentes.executeUpdate();
	            
	            System.out.println("Componente " + entry.getKey() + 
	                    " - Cantidad: " + entry.getValue() + 
	                    " agregado a factura: " + factura.getIdFactura());
	        }

	        System.out.println("Se insertaron " + componenteCantidades.size() + 
	                " tipos de componentes para la factura: " + factura.getIdFactura());
	    }
	}

	// Método para verificar si una factura existe en la BD
	public boolean existeFactura(Connection conn, String idFactura) throws SQLException {
		String sql = "SELECT COUNT(*) FROM Factura WHERE id_factura = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, idFactura);
		ResultSet rs = pstmt.executeQuery();
		rs.next();
		return rs.getInt(1) > 0;
	}

	public void guardarFacturasEnBD(Connection conn) throws SQLException {
		// Primero limpiar las tablas
		String deleteDetalles = "DELETE FROM detalle_factura";
		String deleteFacturas = "DELETE FROM Factura";

		PreparedStatement deleteDetallesStmt = conn.prepareStatement(deleteDetalles);
		PreparedStatement deleteFacturasStmt = conn.prepareStatement(deleteFacturas);

		deleteDetallesStmt.executeUpdate();
		deleteFacturasStmt.executeUpdate();
		System.out.println("Tablas Factura y detalle_factura limpiadas.");

		ArrayList<Factura> facturasEmpresa = Empresa.getInstance().getLasFacturas();

		if (facturasEmpresa == null || facturasEmpresa.isEmpty()) {
			System.out.println("No hay facturas para guardar.");
			return;
		}

		// SQL para insertar facturas
		String sqlFactura = "INSERT INTO Factura (id_factura, id_cliente, costo_total) VALUES (?, ?, ?)";
		PreparedStatement pstmtFactura = conn.prepareStatement(sqlFactura);

		// SQL para insertar detalles de factura con cantidad
		String sqlDetalle = "INSERT INTO detalle_factura (id_factura, id_componente, cantidad) VALUES (?, ?, ?)";
		PreparedStatement pstmtDetalle = conn.prepareStatement(sqlDetalle);

		int facturasGuardadas = 0;
		int detallesGuardados = 0;

		for (Factura factura : facturasEmpresa) {
			// 1. Insertar la factura principal
			pstmtFactura.setString(1, factura.getIdFactura());
			pstmtFactura.setString(2, factura.getCliente().getIdCliente());
			pstmtFactura.setDouble(3, factura.getCostoTotal());
			pstmtFactura.executeUpdate();
			facturasGuardadas++;

			// 2. Contar componentes y sus cantidades
			if (factura.getLosComponentes() != null && !factura.getLosComponentes().isEmpty()) {
				// Usar HashMap para contar cantidades de cada componente
				HashMap<String, Integer> componenteCantidades = new HashMap<>();

				// Contar cuántas veces aparece cada componente
				for (Componente componente : factura.getLosComponentes()) {
					String idComponente = componente.getIdComponente();
					componenteCantidades.put(idComponente, 
							componenteCantidades.getOrDefault(idComponente, 0) + 1);
				}

				// Insertar cada componente único con su cantidad total
				for (Map.Entry<String, Integer> entry : componenteCantidades.entrySet()) {
					pstmtDetalle.setString(1, factura.getIdFactura());
					pstmtDetalle.setString(2, entry.getKey());
					pstmtDetalle.setInt(3, entry.getValue());
					pstmtDetalle.executeUpdate();
					detallesGuardados++;

					System.out.println("Componente " + entry.getKey() + 
							" - Cantidad: " + entry.getValue() + 
							" en factura: " + factura.getIdFactura());
				}
			}
		}

		System.out.println("Se guardaron " + facturasGuardadas + " facturas en la base de datos.");
		System.out.println("Se guardaron " + detallesGuardados + " detalles de factura en la base de datos.");
	}

	public ArrayList<Cliente> cargarClientesDesdeBD(Connection connection) throws SQLException {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM cliente";

		try (PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				String tipoCliente = rs.getString("tipo_cliente");

				if ("Empleado".equals(tipoCliente)) {
					// Crear empleado
					Empleado empleado = new Empleado(
							rs.getString("id_cliente"),
							rs.getString("direccion"),
							rs.getString("telefono"),
							rs.getString("cedula"),
							rs.getString("nombre"),
							(float) (rs.getBigDecimal("sueldo_total") != null ? rs.getBigDecimal("sueldo_total").doubleValue() : 0.0),
							rs.getInt("cant_anios_trabajando"),
							rs.getInt("descuento")
							);
					clientes.add(empleado);
				} else {
					// Para clientes normales y visitantes
					int cantCompras = rs.getInt("cant_compras");

					if (cantCompras > 0) {
						// Es un visitante (cliente con compras registradas)
						Visitante visitante = new Visitante(
							rs.getString("id_cliente"),
							rs.getString("direccion"),
							rs.getString("telefono"),
							rs.getString("cedula"),
							rs.getString("nombre"),
							cantCompras
							);
						clientes.add(visitante);
					} else {
						// Es un cliente normal - crear Cliente base, no Visitante
						// CORREGIDO: Usar clase Cliente base si existe, o manejar apropiadamente
						// Si no tienes clase Cliente base, usar Visitante pero con lógica clara
						Visitante clienteBase = new Visitante(
							rs.getString("id_cliente"),
							rs.getString("direccion"),
							rs.getString("telefono"),
							rs.getString("cedula"),
							rs.getString("nombre"),
							0  // 0 compras para diferenciarlo de visitantes reales
							);
						clientes.add(clienteBase);
					}
				}
			}
		}

		System.out.println("Clientes cargados desde BD: " + clientes.size());
		return clientes;
	}

	/**
	 * Carga y devuelve una lista de componentes desde la base de datos
	 */
	public ArrayList<Componente> cargarComponentesDesdeBD(Connection connection) throws SQLException {
		ArrayList<Componente> componentes = new ArrayList<>();
		String sql = "SELECT * FROM componente";

		try (PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				String tipoComponente = rs.getString("tipo_componente");

				// Usar el constructor más básico que tengas para cada tipo
				switch (tipoComponente) {
				case "MicroProcesador":
					MicroProcesador mp = new MicroProcesador(
							rs.getString("id_componente"),
							rs.getString("numero_serie"),
							(float)rs.getBigDecimal("precio").doubleValue(),
							rs.getInt("cantidad_disponible"),
							rs.getString("marca"),
							rs.getString("modelo"),
							rs.getString("tipo_conexion"),
							(float)rs.getBigDecimal("vel_procesamiento_ghz").doubleValue()
							);
					componentes.add(mp);
					break;

				case "RAM":
					RAM ram = new RAM(
							rs.getString("id_componente"),
							rs.getString("numero_serie"),
							(float)rs.getBigDecimal("precio").doubleValue(),
							rs.getInt("cantidad_disponible"),
							rs.getString("marca"),
							rs.getString("modelo"),
							(float)rs.getBigDecimal("cant_gb").doubleValue(),
							rs.getString("tipo_memoria")
							);
					componentes.add(ram);
					break;

				case "DiscoDuro":
					DiscoDuro dd = new DiscoDuro(
							rs.getString("id_componente"),
							rs.getString("numero_serie"),
							(float)rs.getBigDecimal("precio").doubleValue(),
							rs.getInt("cantidad_disponible"),
							rs.getString("marca"),
							rs.getString("modelo"),
							rs.getString("tipo_conexion"),
							(float)rs.getBigDecimal("cant_gb").doubleValue()
							);
					componentes.add(dd);
					break;

				case "TarjetaMadre":
					// Para TarjetaMadre, manejar las conexiones
					String conexionesStr = rs.getString("conexiones");
					ArrayList<String> conexiones = new ArrayList<>();
					if (conexionesStr != null && !conexionesStr.isEmpty()) {
						String[] partes = conexionesStr.split(",");
						for (String parte : partes) {
							conexiones.add(parte.trim());
						}
					}

					TarjetaMadre tm = new TarjetaMadre(
							rs.getString("id_componente"),
							rs.getString("numero_serie"),
							(float)rs.getBigDecimal("precio").doubleValue(),
							rs.getInt("cantidad_disponible"),
							rs.getString("marca"),
							rs.getString("modelo"),
							rs.getString("conector"),
							rs.getString("tipo_ram"),
							conexiones
							);
					componentes.add(tm);
					break;
				}
			}
		}

		System.out.println("Componentes cargados desde BD: " + componentes.size());
		return componentes;
	}

	/**
	 * Carga y devuelve una lista de facturas desde la base de datos
	 * Requiere que los clientes y componentes ya estén cargados en Empresa.getInstance()
	 */
	public ArrayList<Factura> cargarFacturasDesdeBD(Connection connection) throws SQLException {
		ArrayList<Factura> facturas = new ArrayList<>();
		String sql = "SELECT * FROM Factura";

		try (PreparedStatement stmt = connection.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				String idFactura = rs.getString("id_factura");
				String idCliente = rs.getString("id_cliente");
				double costoTotalBD = rs.getDouble("costo_total");

				// Buscar el cliente en la instancia de Empresa
				Cliente cliente = null;
				for (Cliente c : Empresa.getInstance().getLosClientes()) {
					if (c.getIdCliente().equals(idCliente)) {
						cliente = c;
						break;
					}
				}

				if (cliente != null) {
					// Crear factura con constructor básico
					Factura factura = new Factura(idFactura, cliente);

					// Cargar componentes de esta factura
					cargarComponentesDeFactura(connection, factura);

					// VERIFICACIÓN: Comparar total calculado vs BD
					

					facturas.add(factura);
				} else {
					System.out.println("ADVERTENCIA: Cliente no encontrado para factura " + idFactura + " (Cliente ID: " + idCliente + ")");
				}
			}
		}

		System.out.println("Facturas cargadas desde BD: " + facturas.size());
		return facturas;
	}

	// Método mejorado para cargar componentes con mejor manejo de errores
	private void cargarComponentesDeFactura(Connection connection, Factura factura) throws SQLException {
		String sql = "SELECT df.id_componente, df.cantidad FROM detalle_factura df WHERE df.id_factura = ?";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, factura.getIdFactura());

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					String idComponente = rs.getString("id_componente");
					int cantidad = rs.getInt("cantidad");

					// Buscar el componente en la instancia de Empresa
					Componente componenteOriginal = null;
					for (Componente c : Empresa.getInstance().getLosComponentes()) {
						if (c.getIdComponente().equals(idComponente)) {
							componenteOriginal = c;
							break;
						}
					}

					if (componenteOriginal != null) {
						// Agregar el componente tantas veces como indica la cantidad
						for (int i = 0; i < cantidad; i++) {
							factura.agregarComponente(componenteOriginal);
						}
						System.out.println("  Agregados " + cantidad + " de componente " + idComponente + " a factura " + factura.getIdFactura());
					} else {
						System.out.println("ADVERTENCIA: Componente no encontrado: " + idComponente + " para factura " + factura.getIdFactura());
					}
				}
			}
		}
	}

}