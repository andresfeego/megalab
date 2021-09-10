package auxiliares;

import java.util.Calendar;
import java.util.Date;

import com.sun.javafx.binding.StringFormatter;

import conexion.conexionBusqueda;

public class Paciente {
	
	private String id;
	private int tipoId;
	private String nombres;
	private String apellidos;
	private Date fechaNacimiento;
	private String edad;
	private int genero;
	private int zonaResidencial;
	private String ciudad;
	private String direccion;
	private int parentesco;
	private String num_Carnet;
	private String email1;
	private String email2;
	private String telefono1;
	private String telefono2;

	
	public Paciente(String id, int tipoId, String nombres, String apellidos,
			Date fechaNacimiento, String edad, int genero, int zonaResidencial,
			String ciudad, String direccion, int parentesco, String num_Carnet,
			String email1, String email2, String telefono1, String telefono2) {
		super();
		this.id = id;
		this.tipoId = tipoId;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.edad = edad;
		this.genero = genero;
		this.zonaResidencial = zonaResidencial;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.parentesco = parentesco;
		this.num_Carnet = num_Carnet;
		this.email1 = email1;
		this.email2 = email2;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		
	}
	
	
	public String getId() {
		return id;
	}
	public int getTipoId() {
		return tipoId;
	}
	public String getNombres() {
		return nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public String getEdad() {
		return edad;
	}
	public int getGenero() {
		return genero;
	}
	public int getZonaResidencial() {
		return zonaResidencial;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public int getParentesco() {
		return parentesco;
	}
	public String getNum_Carnet() {
		return num_Carnet;
	}
	public String getEmail1() {
		return email1;
	}
	public String getEmail2() {
		return email2;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setTipoId(int tipoId) {
		this.tipoId = tipoId;
	}


	public void setNombres(String nombres) {
		this.nombres = nombres;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public void setEdad(String edad) {
		this.edad = edad;
	}


	public void setGenero(int genero) {
		this.genero = genero;
	}


	public void setZonaResidencial(int zonaResidencial) {
		this.zonaResidencial = zonaResidencial;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public void setParentesco(int parentesco) {
		this.parentesco = parentesco;
	}


	public void setNum_Carnet(String num_Carnet) {
		this.num_Carnet = num_Carnet;
	}


	public void setEmail1(String email1) {
		this.email1 = email1;
	}


	public void setEmail2(String email2) {
		this.email2 = email2;
	}


	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}


	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}


	public String getGeneroString(){
		System.err.println(genero);
		if (genero==1) {
			return "Masculino";
		}else{
		return "Femenino";
		}
	}
	
	public String getGeneroSigla(){
		System.err.println(genero);
		if (genero==1) {
			return "M";
		}else{
		return "F";
		}
	}
	
	public String getResidenciaSigla(){
		if (zonaResidencial==1) {
			return "U";
		}else{
		return "R";
		}
	}
	
	
	@Override
	public String toString() {
		StringBuffer SF=new StringBuffer();
		SF.append("nombre"+nombres);
		SF.append("apellidos"+apellidos);
		return SF.toString();
	}
	
	public String calcularEdad(){
Date FechaNac=this.getFechaNacimiento();
		Calendar fechaActual = Calendar.getInstance();
        Calendar FechaNacimi = Calendar.getInstance();


		 if(FechaNac!=null){
		        FechaNacimi.setTime(FechaNac);

			 if(fechaActual.getTimeInMillis()<FechaNacimi.getTimeInMillis()){
				 return "error - fecha exedida";
			 }else{
	        //Se restan la fecha actual y la fecha de nacimiento
	        int aos = fechaActual.get(Calendar.YEAR)- FechaNacimi.get(Calendar.YEAR);
	        int mes =fechaActual.get(Calendar.MONTH)- FechaNacimi.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DATE)- FechaNacimi.get(Calendar.DATE);
	        //Se ajusta el año dependiendo el mes y el día
	        if(mes<0 || (mes==0 && dia<0)){
	            aos--;
	        }
	        
	        if (dia<0||mes==0&&dia<0) {
				mes--;
			}
	        
	        if (aos>=5) {
	        	return aos+" Años";
			} else {
				if (aos<5&&aos>=1) {

					String meses="";
					if (mes<0)meses= 12+(mes)+"- Meses";
					if (mes>0)meses= (mes)+"- Meses";
					
					return aos+" Años "+meses;
				} else {
					if (aos<1&&mes!=0) {

						String meses="";
						if (mes<0)meses= 12+(mes)+" Meses";
						if (mes>0)meses= (mes)+" Meses";
							
							return meses;
					} else {
						return fechaActual.get(Calendar.DAY_OF_YEAR)-FechaNacimi.get(Calendar.DAY_OF_YEAR)+" Dias de nacido";
					}
				
			}
		}
	        
	        
	        
	      
			 }
		 }
	        else{
			 return "Sin fecha de nacimiento";
			 
		 }
	
	}
	
	
	
	public String tipoDocString(){
		String salida="";
		switch (tipoId) {
		case 1:
			salida="CC";
			break;
			
		case 2:
			salida="AS";
			break;
			
		case 3:
			salida="CE";
			break;
			
		case 4:
			salida="MS";
			break;
			
		case 5:
			salida="NU";
			break;
			
		case 6:
			salida="PA";
			break;
			
		case 7:
			salida="RC";
			break;
			
		case 8:
			salida="TI";
			break;

		default:
			break;
		}
		
		return salida;
	}
	
	
	public String nombresCompletos(){
		return nombres+" "+apellidos;
	}
	
	public String PrimerNombre(){
		String[] salida=nombres.split(" ");
		return salida[0];
	}
	
	public String SegundoNombre(){
		String[] salida=nombres.split(" ");
			String segNom="";
			if (salida.length!=1) {
				segNom=salida[1];
			}
		return segNom;
	}
	
	public String primerApellido(){
		String[] salida=apellidos.split(" ");
		return salida[0];
	}
	
	public String segundoApellido(){
		String[] salida=apellidos.split(" ");
		String segAp="";
		if (salida.length!=1) {
			segAp=salida[1];
		}
	return segAp;
	}
	
	public String edadNum(){
Date FechaNac=this.getFechaNacimiento();
		Calendar fechaActual = Calendar.getInstance();
        Calendar FechaNacimi = Calendar.getInstance();


		 if(FechaNac!=null){
		        FechaNacimi.setTime(FechaNac);

			 if(fechaActual.getTimeInMillis()<FechaNacimi.getTimeInMillis()){
				 return "";
			 }else{
	        //Se restan la fecha actual y la fecha de nacimiento
	        int aos = fechaActual.get(Calendar.YEAR)- FechaNacimi.get(Calendar.YEAR);
	        int mes =fechaActual.get(Calendar.MONTH)- FechaNacimi.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DATE)- FechaNacimi.get(Calendar.DATE);
	        //Se ajusta el año dependiendo el mes y el día
	        if(mes<0 || (mes==0 && dia<0)){
	            aos--;
	        }
	        
	        if (dia<0||mes==0&&dia<0) {
				mes--;
			}
	        
	        if (aos>=5) {
	        	return aos+"";
			} else {
				if (aos<5&&aos>=1) {

					String meses="";
					if (mes<0)meses= 12+(mes)+"";
					if (mes>0)meses= (mes)+"";
					
					return aos+"";
				} else {
					if (aos<1&&mes!=0) {

						String meses="";
						if (mes<0)meses= 12+(mes)+"";
						if (mes>0)meses= (mes)+"";
							
							return meses;
					} else {
						return fechaActual.get(Calendar.DAY_OF_YEAR)-FechaNacimi.get(Calendar.DAY_OF_YEAR)+"";
					}
				
			}
		}
	        
	        
	        
	      
			 }
		 }
	        else{
			 return "";
			 
		 }
	
	}
	
	public String edadTipo(){
Date FechaNac=this.getFechaNacimiento();
		Calendar fechaActual = Calendar.getInstance();
        Calendar FechaNacimi = Calendar.getInstance();


		 if(FechaNac!=null){
		        FechaNacimi.setTime(FechaNac);

			 if(fechaActual.getTimeInMillis()<FechaNacimi.getTimeInMillis()){
				 return "";
			 }else{
	        //Se restan la fecha actual y la fecha de nacimiento
	        int aos = fechaActual.get(Calendar.YEAR)- FechaNacimi.get(Calendar.YEAR);
	        int mes =fechaActual.get(Calendar.MONTH)- FechaNacimi.get(Calendar.MONTH);
	        int dia = fechaActual.get(Calendar.DATE)- FechaNacimi.get(Calendar.DATE);
	        //Se ajusta el año dependiendo el mes y el día
	        if(mes<0 || (mes==0 && dia<0)){
	            aos--;
	        }
	        
	        if (dia<0||mes==0&&dia<0) {
				mes--;
			}
	        
	        if (aos>=5) {
	        	return "1";
			} else {
				if (aos<5&&aos>=1) {

			
					
					return "1";
				} else {
					if (aos<1&&mes!=0) {

					
							
							return "2";
					} else {
						return "3";
					}
				
			}
		}
	        
	        
	        
	      
			 }
		 }
	        else{
			 return "";
			 
		 }
	
	}

	
	public String codCiudad(){
		String codCiudad=conexionBusqueda.getInstance().idCiudadXnombre(ciudad)+"";
		if (codCiudad.length()==4) {
			codCiudad="0"+codCiudad;
		}

		return codCiudad;
	}
	
}
