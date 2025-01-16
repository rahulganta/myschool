// es.js

const messages = {
        appname: 'MiEscuela',
        school: "Escuela ",
        user: 'Usuario | Usuarios',
        student: 'Estudiante | Estudiantes',
        nav: {
            home: 'Inicio',
            mycourses: 'Mis Cursos',
            messages: "Mensajes",
            staff: 'Personal',
            students: 'Estudiantes',
            attendance: 'Asistencia',
            reports: 'Informes',
            schedule: 'Horario',
            classwork: 'Trabajo en Clase',

            aboutus: 'Sobre Nosotros',
            test: 'Prueba'
        },

        // Page headers, other headers, card titles and etc..
        hdr: {
            home: 'Inicio',
            mycourses: 'Mis Cursos',
            messages: "Mensajes",
            staff: 'Personal',
            teachers: 'PROFESORES',
            admins: 'ADMINISTRADORES',
            students: 'Estudiantes',
            attendance: 'Asistencia',
            reports: 'Informes',
            schedule: 'Horario',
            aboutus: 'Sobre Nosotros',
            memberlogin: 'Inicio de sesión de miembro',
            schoollist: 'Lista de escuelas',
            schoolInfo: 'Información de la escuela',
            mymessages: 'Mis Mensajes',
            schoolannouncements: 'Anuncios Escolares',
            coursemessages: 'Mensajes del Curso',
            coursework: 'Trabajo del Curso',
            courseattendance: 'Asistencia al Curso',

            contactus: 'Contáctenos',
            test: 'Prueba'
        },

        // Buttons and Links
        btn: {
            submit: 'ENVIAR',
            cancel: 'CANCELAR',
            close: 'CERRAR',
            confirm: 'CONFIRMAR',
            save: 'GUARDAR',
            here: 'AQUÍ',
            add: 'AGREGAR',
            edit: 'EDITAR',
            update: 'ACTUALIZAR',
            delete: 'ELIMINAR',
            school: 'ESCUELA',
            course: 'CURSO',
            user: 'USUARIO',
            teacher: 'PROFESOR',
            admin: 'ADMINISTRADOR',

            login: 'INICIAR SESIÓN',
            signout: 'CERRAR SESIÓN',
            settings: 'CONFIGURACIÓN',
            userprofile: 'PERFIL DE USUARIO',

            addschool: 'AGREGAR ESCUELA',
            viewschool: 'VER ESCUELA',
            editschool: 'EDITAR ESCUELA',
            updateschool: 'ACTUALIZAR ESCUELA',
            inactivateschool: 'DESACTIVAR ESCUELA',
            addschoolannouncement: 'AGREGAR ANUNCIO ESCOLAR',

            viewadmin: 'VER ADMINISTRADOR',
            addadmin: 'AGREGAR ADMINISTRADOR',
            editadmin: 'EDITAR ADMINISTRADOR',
            updateadmin: 'ACTUALIZAR ADMINISTRADOR',
            deleteadmin: 'ELIMINAR ADMINISTRADOR',

            adduser: 'AGREGAR USUARIO',
            edituser: 'EDITAR USUARIO',
            updateuser: 'ACTUALIZAR USUARIO',
            deleteuser: 'ELIMINAR USUARIO',

            viewteacher: 'VER PROFESOR',
            addteacher: 'AGREGAR PROFESOR',
            editteacher: 'EDITAR PROFESOR',
            updateteacher: 'ACTUALIZAR PROFESOR',
            deleteteacher: 'ELIMINAR PROFESOR',

            viewstudent: 'VER ESTUDIANTE',
            addstudent: 'AGREGAR ESTUDIANTE',
            editstudent: 'EDITAR ESTUDIANTE',
            updatestudent: 'ACTUALIZAR ESTUDIANTE',
            deletestudent: 'ELIMINAR ESTUDIANTE',

            addcourse: 'AGREGAR CURSO',
            editcourse: 'EDITAR CURSO',
            updatecourse: 'ACTUALIZAR CURSO',
            viewcourse: 'VER CURSO',
            inactivatecourse: 'DESACTIVAR CURSO',
            addcourseannouncement: 'AGREGAR ANUNCIO DEL CURSO',
            addcoursework: 'AGREGAR TRABAJO DEL CURSO',

            createclasswork: 'CREAR TRABAJO DE CLASE',
            addfile: 'AGREGAR ARCHIVO',

            takeattendance: 'TOMAR ASISTENCIA',

        },

        //Table columns headers
        tbl: {
            schoolname: 'NOMBRE DE LA ESCUELA',
            displayname: 'NOMBRE MOSTRADO',
            franchise: 'FRANQUICIA',
            correspondent: 'CORRESPONSAL',
            status: 'ESTADO',
            username: 'NOMBRE DE USUARIO',
            firstname:'NOMBRE',
            lastname:'APELLIDOS',
            email:'CORREO ELECTRÓNICO',
            student: 'ESTUDIANTE',


        },

        //Form Labels, other Labels, text and etc..
        label: {
            username: 'Nombre de usuario',
            name: 'Nombre',
            email: 'Correo electrónico',
            phone: 'Teléfono',
            country: 'País',
            address: 'Dirección',
            line1: 'Línea 1',
            line2: 'Línea 2',
            city: 'Ciudad',
            state: 'Estado',
            zip: 'Código Postal',
            message: 'Mensaje',
            date: 'Fecha',
            displayname: 'Nombre mostrado',
            franchisename: 'Nombre de franquicia',
            correspondent: 'Corresponsal',
            status: 'Estado',

            course: 'Curso',
            instructor: 'Instructor',

            note: 'NOTA',
            useformtocontactus: 'Por favor, utilice el siguiente formulario para contactarnos.',

            itemsselected: 'No hay elementos seleccionados | Un elemento seleccionado | {n} elementos seleccionados',
        },

        //Placeholders
        plch: {
            enter: 'Introducir',
            username: 'Nombre de usuario',
            password: 'Contraseña',
            name: 'Nombre',
            email: 'sucorreo@email.com',
            phone: '999-999-9999',
            country: 'España',
            Address: 'Dirección',
            line1: 'HNo 5-5-515/1/5, Vidyanagar',
            line2: '',
            city: 'Madrid',
            state: 'Madrid',
            zip: '1234',
            announcement: 'Escribir anuncio escolar',
            classworkdesc: 'Descripción del trabajo de clase'
        },

        //Messages and Error Messages
        msg: {
            pageunderdev: 'Gracias por visitar esta página, está en desarrollo.',
            contactustodonate: 'Si desea donar, por favor contáctenos',

            loading: 'Cargando...' ,
            nothing: 'No hay nada que mostrar aquí.' ,
            allrights: 'Todos los derechos reservados',
        },
        error: {
            passwordnotmatching: 'La contraseña y la confirmación de contraseña no coinciden',
        },

        option: {
            defaulttheme: "Tema predeterminado",
            darktheme: "Tema oscuro",
        },

        toast: {
            added: "¡Agregado con éxito!!",
            updated: "¡Actualizado con éxito!!",
            archived: "¡Archivado con éxito!!",
        },

        //Its same in all locale files so no need to add in other local files
        localeoption: {
            english: 'English',
            telugu: 'తెలుగు',
            spanish: 'Español',
            hindi: 'हिंदी',
        },

        SCHOOLNAME: 'Nombre de la Escuela',
        copyright: 'Copyright © 2020 @:appname . Todos los derechos reservados.',
        items: 'Sin elementos | Un elemento | {count} elementos',
        linked: '@:message.hello, Bienvenido a @:nav.ourmission, su ID se ha creado con éxito',
};

export default messages