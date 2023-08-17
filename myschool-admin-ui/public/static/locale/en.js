//  Translated Locale Messages to English
const messages = {
    en: {
        appname: 'MySchool',
        school: "School ",
        user: 'User | Users',
        student: 'Student | Students',
        nav: {
            home: 'Home',
            mycourses: 'MyCourses',
            messages: "Messages",
            staff: 'Staff',
            students: 'Students',
            attendance: 'Attendance',
            reports: 'Reports',
            schedule: 'Schedule',
            classwork: 'Classwork',

            aboutus: 'About Us',
            test: 'Test'
        },

        // Page headers, other headers, card titles and etc..
        hdr: {
            home: 'Home',
            mycourses: 'MyCourses',
            messages: "Messages",
            staff: 'Staff',
            teachers: 'TEACHERS',
            admins: 'ADMINS',
            students: 'Students',
            attendance: 'Attendance',
            reports: 'Reports',
            schedule: 'Schedule',
            aboutus: 'About Us',
            memberlogin: 'Member Login',
            schoollist: 'School List',
            schoolInfo: 'School Info',
            mymessages: 'My Messages',
            schoolannouncements: 'School Announcements',
            coursemessages: 'Course Messages',
            coursework: 'Course Work',
            courseattendance: 'Course Attendance',

            contactus: 'Contact Us',
            test: 'Test'
        },

        // Buttons and Links
        btn: {
            submit: 'SUBMIT',
            cancel: 'CANCEL',
            close: 'CLOSE',
            confirm: 'CONFIRM',
            save: 'SAVE',
            here: 'HERE',
            add: 'ADD',
            edit: 'EDIT',
            update: 'UPDATE',
            delete: 'DELETE',
            school: 'SCHOOL',
            course: 'COURSE',
            user: 'USER',
            teacher: 'TEACHER',
            admin: 'ADMIN',

            login: 'LOGIN',
            signout: 'SIGN OUT',
            settings: 'SETTINGS',
            userprofile: 'USER PROFILE',

            addschool: 'ADD SCHOOL',
            viewschool: 'VIEW SCHOOL',
            editschool: 'EDIT SCHOOL',
            updateschool: 'UPDATE SCHOOL',
            inactivateschool: 'INACTIVATE SCHOOL',
            addschoolannouncement: 'ADD SCHOOL ANNOUNCEMENT',

            viewadmin: 'VIEW ADMIN',
            addadmin: 'ADD ADMIN',
            editadmin: 'EDIT ADMIN',
            updateadmin: 'UPDATE ADMIN',
            deleteadmin: 'DELETE ADMIN',

            adduser: 'ADD USER',
            edituser: 'EDIT USER',
            updateuser: 'UPDATE USER',
            deleteuser: 'DELETE USER',

            viewteacher: 'VIEW TEACHER',
            addteacher: 'ADD TEACHER',
            editteacher: 'EDIT TEACHER',
            updateteacher: 'UPDATE TEACHER',
            deleteteacher: 'DELETE TEACHER',


            viewstudent: 'VIEW STUDENT',
            addstudent: 'ADD STUDENT',
            editstudent: 'EDIT STUDENT',
            updatestudent: 'UPDATE STUDENT',
            deletestudent: 'DELETE STUDENT',

            addcourse: 'ADD COURSE',
            editcourse: 'EDIT COURSE',
            updatecourse: 'UPDATE COURSE',
            viewcourse: 'VIEW COURSE',
            inactivatecourse: 'INACTIVATE COURSE',
            addcourseannouncement: 'ADD COURSE ANNOUNCEMENT',
            addcoursework: 'ADD COURSE WORK',

            createclasswork: 'CREATE CLASS WORK',
            addfile: 'ADD FILE',

            takeattendance: 'TAKE ATTENDANCE',

        },

        //Table columns headers
        tbl: {
            schoolname: 'SCHOOL NAME',
            displayname: 'DISPLAY NAME',
            franchise: 'FRANCHISE',
            correspondent: 'CORRESPONDENT',
            status: 'STATUS',
            username: 'USERNAME',
            firstname:'FIRST NAME',
            lastname:'LAST NAME',
            email:'EMAIL',
            student: 'STUDENT',


        },

        //Form Labels, other Labels, text and etc..
        label: {
            username: 'Username',
            name: 'Name',
            email: 'Email',
            phone: 'Phone',
            country: 'Country',
            address: 'Address',
            line1: 'Line 1',
            line2: 'Line 2',
            city: 'City',
            state: 'State',
            zip: 'Zip/Postal Code',
            message: 'Message',
            date: 'Date',
            displayname: 'Display Name',
            franchisename: 'Franchise Name',
            correspondent: 'Correspondent',
            status: 'Status',

            course: 'Course',
            instructor: 'Instructor',

            note: 'NOTE',
            useformtocontactus: 'Please use below form to contact us.',

            itemsselected: 'No Item Selected | One Item Selected | {n} Items Selected',
        },

        //Placeholders
        plch: {
            enter: 'Enter',
            username: 'Username',
            password: 'Password',
            name: 'Name',
            email: 'youremail@email.com',
            phone: '999-999-9999',
            country: 'India',
            Address: 'Address',
            line1: 'HNo 5-5-515/1/5, Vidyanagar',
            line2: '',
            city: 'Hyderabad',
            state: 'Telangana',
            zip: '1234',
            announcement: 'Write school announcement',
            classworkdesc: 'Class Work Description'
        },

        //Messages and Error Messages
        msg: {
            pageunderdev: 'Thanks for visiting this page, its under development.',
            contactustodonate: 'If you are willing to donate, please contact us',

            loading: 'Loading...' ,
            nothing: 'Nothing to show here.' ,
            allrights: 'All Rights Reserved',
        },

        option: {
            defaulttheme: "Default Theme",
            darktheme: "Dark Theme",
        },

        toast: {
            added: "added successfully!!",
            updated: "updated successfully!!",
            archived: "archived successfully!!",
        },

        //Its same in all locale files so no need to add in other local files
        localeoption: {
            english: 'English',
            telugu: 'తెలుగు',
            spanish: 'Español',
            hindi: 'हिंदी',
        },

        SCHOOLNAME: 'School Name',
        copyright: 'Copyright © 2020 @:appname .  All Rights Reserved.',
        items: 'No Items | one Item | {count} Items',
        linked: '@:message.hello, Welcome to @:nav.ourmission, your id has been created successfully',
    }
};

export const dateTimeFormats = {
    en: {
        shortdate: {
            year: 'numeric', month: 'short', day: 'numeric'
        },
        longdate: {
            year: 'numeric', month: 'short', day: 'numeric',
            weekday: 'short', hour: '2-digit', minute: 'numeric', second:'numeric', hour12: true,
            timeZoneName: 'short'
        }
    },
    te: {
        shortdate: {
            year: 'numeric', month: 'short', day: 'numeric'
        },
        longdate: {
            year: 'numeric', month: 'long', day: 'numeric',
            weekday: 'long', hour: 'numeric', minute: 'numeric', second:'numeric',hour12: true,
            timeZoneName: 'short'
        }
    }
};

export const numberFormats = {
    'en': {
        currency: {
            style: 'currency',
            currency: 'USD'
        }
    },
    'ja-JP': {
        currency: {
            style: 'currency',
            currency: 'JPY',
            currencyDisplay: 'symbol'
        }
    }
}

export default messages
