//  Translated Locale Messages to English
const messages = {
    en: {
        appname: 'MySchool',
        nav: {
            home: 'HOME',
            aboutus: 'ABOUT US',
            gallery: 'GALLERY',
            contact: 'CONTACT',
            products: 'PRODUCTS',
            activities: 'ACTIVITIES',
            test: 'TEST'
        },

        // Buttons and Links
        btn: {
            login: 'LOGIN',
            signout: 'Sign Out',
            submit: 'SUBMIT',
            cancel: 'CANCEL',
            close: 'CLOSE',
            confirm: 'CONFIRM',
            save: 'SAVE',
            contactsinfo: 'Contacts Info',
            here: 'here',
            settings: 'Settings'
        },

        //Page titles, card titles, headers, etc
        title: {
            memberlogin: 'Member Login',
            quote1: 'Every one can do something for the better future!!',
            quote2: 'Education is the most powerful weapon which you can use to change the world!',
            quote3: 'Life and sports can not be separated; They are one!',
            webelieve: 'We Believe',
            edustatement1: 'Education is the path forward in eradicating poverty and building a good society.',

            education: 'Education',
            sports: 'Sports',
            cultural: 'Cultural',
            helpinghand: 'Helping Hand',

            missionstatement: 'Mission Statement',
            goal: 'Our Goal',
            history: 'History',

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
            contactmessage: 'Write what you are contacting us for',
        },

        //Messages and Error Messages
        msg: {
            pageunderdev: 'Thanks for visiting this page, its under development.',
            contactustodonate: 'If you are willing to donate, please contact us',

            loading: 'Loading...' ,
            nothing: 'Nothing to show here.' ,
            allrights: 'All Rights Reserved',
        },

        //Its same in all locale files so no need to add in other local files
        localeoption: {
            english: 'English',
            telugu: 'తెలుగు',
            spanish: 'Español',
        },

        option: {
            defaulttheme: "Default Theme",
            darktheme: "Dark Theme",
        },

        copyright: 'Copyright © 2020 @:appname .  All Rights Reserved.',
        user: 'User | Users',
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
};

export default messages
