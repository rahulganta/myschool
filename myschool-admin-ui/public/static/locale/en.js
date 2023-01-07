//  Translated Locale Messages to English
const messages = {
    en: {
        appname: 'MySchool',
        school: "School ",
        user: 'User | Users',
        nav: {
            home: 'Home',
            mycourses: 'My Courses',
            messages: "Messages",
            staff: 'Staff',
            students: 'Students',
            attendance: 'Attendance',
            reports: 'Reports',
            schedule: 'Schedule',
            aboutus: 'About Us',
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

            addadmin: 'ADD ADMIN',
            editadmin: 'EDIT ADMIN',
            updateadmin: 'UPDATE ADMIN',
            adduser: 'ADD USER',
            edituser: 'EDIT USER',
            updateuser: 'UPDATE USER',

            addcourse: 'ADD COURSE',
            editcourse: 'EDIT COURSE',
            updatecourse: 'UPDATE COURSE',
            viewcourse: 'VIEW COURSE',
            inactivatecourse: 'INACTIVATE COURSE',

            createclasswork: 'CREATE CLASS WORK',
            addfile: 'ADD FILE',

            addschoolannouncement: 'ADD SCHOOL ANNOUNCEMENT',

        },

        //Table columns headers
        tbl: {
            schoolname: 'SCHOOL NAME',
            displayname: 'DISPLAY NAME',
            franchise: 'FRANCHISE',
            correspondent: 'CORRESPONDENT',
            status: 'STATUS'

        },

        //Page titles, card titles, headers, etc
        title: {
            memberlogin: 'Member Login',
            schoollist: 'School List',
            schoolInfo: 'School Info',
            mymessages: 'My Messages',
            schoolannouncements: 'School Announcements',
            mycourses: 'My Courses',
            contactus: 'Contact Us',
        },

        //Form Labels and other Labels
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

            displayname: 'Display Name',
            franchisename: 'Franchise Name',
            correspondent: 'Correspondent',
            status: 'Status',

            course: 'Course',
            instructor: 'Instructor',

            note: 'NOTE',
            useformtocontactus: 'Please use below form to contact us.',
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
            weekday: 'short', hour: 'numeric', minute: 'numeric', hour12: false
        }
    },
    te: {
        shortdate: {
            year: 'numeric', month: 'short', day: 'numeric'
        },
        longdate: {
            year: 'numeric', month: 'long', day: 'numeric',
            weekday: 'long', hour: 'numeric', minute: 'numeric', hour12: true
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
