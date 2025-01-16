// hi.js

const messages = {
        appname: 'मेरा स्कूल',
        school: "स्कूल ",
        user: 'प्रयोगकर्ता | उपयोगकर्ता',
        student: 'छात्र | छात्र',
        nav: {
            home: 'होम',
            mycourses: 'मेरे पाठ्यक्रम',
            messages: "संदेश",
            staff: 'स्टाफ',
            students: 'छात्र',
            attendance: 'अटेंडेंस',
            reports: 'रिपोर्ट्स',
            schedule: 'शेड्यूल',
            classwork: 'क्लासरूम कार्य',

            aboutus: 'हमारे बारे में',
            test: 'परीक्षा'
        },

        // Page headers, other headers, card titles and etc..
        hdr: {
            home: 'होम',
            mycourses: 'मेरे पाठ्यक्रम',
            messages: "संदेश",
            staff: 'स्टाफ',
            teachers: 'शिक्षक',
            admins: 'प्रशासक',
            students: 'छात्र',
            attendance: 'अटेंडेंस',
            reports: 'रिपोर्ट्स',
            schedule: 'शेड्यूल',
            aboutus: 'हमारे बारे में',
            memberlogin: 'सदस्य लॉगिन',
            schoollist: 'स्कूल सूची',
            schoolInfo: 'स्कूल जानकारी',
            mymessages: 'मेरे संदेश',
            schoolannouncements: 'स्कूल घोषणाएं',
            coursemessages: 'पाठ्यक्रम संदेश',
            coursework: 'पाठ्यक्रम कार्य',
            courseattendance: 'पाठ्यक्रम उपस्थिति',

            contactus: 'हमसे संपर्क करें',
            test: 'परीक्षा'
        },

        // Buttons and Links
        btn: {
            submit: 'सबमिट करें',
            cancel: 'रद्द करें',
            close: 'बंद करें',
            confirm: 'पुष्टि करें',
            save: 'सहेजें',
            here: 'यहां',
            add: 'जोड़ें',
            edit: 'संपादित करें',
            update: 'अपडेट करें',
            delete: 'हटाएं',
            school: 'स्कूल',
            course: 'पाठ्यक्रम',
            user: 'प्रयोगकर्ता',
            teacher: 'शिक्षक',
            admin: 'प्रशासक',

            login: 'लॉगिन',
            signout: 'लॉग आउट',
            settings: 'सेटिंग्स',
            userprofile: 'यूजर प्रोफाइल',

            addschool: 'स्कूल जोड़ें',
            viewschool: 'स्कूल देखें',
            editschool: 'स्कूल संपादित करें',
            updateschool: 'स्कूल अपडेट करें',
            inactivateschool: 'स्कूल निष्क्रिय करें',
            addschoolannouncement: 'स्कूल घोषणा जोड़ें',

            viewadmin: 'प्रशासक देखें',
            addadmin: 'प्रशासक जोड़ें',
            editadmin: 'प्रशासक संपादित करें',
            updateadmin: 'प्रशासक अपडेट करें',
            deleteadmin: 'प्रशासक हटाएं',

            adduser: 'प्रयोगकर्ता जोड़ें',
            edituser: 'प्रयोगकर्ता संपादित करें',
            updateuser: 'प्रयोगकर्ता अपडेट करें',
            deleteuser: 'प्रयोगकर्ता हटाएं',

            viewteacher: 'शिक्षक देखें',
            addteacher: 'शिक्षक जोड़ें',
            editteacher: 'शिक्षक संपादित करें',
            updateteacher: 'शिक्षक अपडेट करें',
            deleteteacher: 'शिक्षक हटाएं',

            viewstudent: 'छात्र देखें',
            addstudent: 'छात्र जोड़ें',
            editstudent: 'छात्र संपादित करें',
            updatestudent: 'छात्र अपडेट करें',
            deletestudent: 'छात्र हटाएं',

            addcourse: 'पाठ्यक्रम जोड़ें',
            editcourse: 'पाठ्यक्रम संपादित करें',
            updatecourse: 'पाठ्यक्रम अपडेट करें',
            viewcourse: 'पाठ्यक्रम देखें',
            inactivatecourse: 'पाठ्यक्रम निष्क्रिय करें',
            addcourseannouncement: 'पाठ्यक्रम घोषणा जोड़ें',
            addcoursework: 'पाठ्यक्रम कार्य जोड़ें',

            createclasswork: 'क्लासरूम कार्य बनाएं',
            addfile: 'फ़ाइल जोड़ें',

            takeattendance: 'अटेंडेंस लें',

        },

        //Table columns headers
        tbl: {
            schoolname: 'स्कूल का नाम',
            displayname: 'प्रदर्शन नाम',
            franchise: 'फ़्रेंचाइज़ी',
            correspondent: 'पत्राचार',
            status: 'स्थिति',
            username: 'यूज़रनेम',
            firstname:'पहला नाम',
            lastname:'अंतिम नाम',
            email:'ईमेल',
            student: 'छात्र',

        },

        //Form Labels, other Labels, text and etc..
        label: {
            username: 'यूज़रनेम',
            name: 'नाम',
            email: 'ईमेल',
            phone: 'फ़ोन',
            country: 'देश',
            address: 'पता',
            line1: 'लाइन 1',
            line2: 'लाइन 2',
            city: 'शहर',
            state: 'राज्य',
            zip: 'ज़िप/पोस्टल कोड',
            message: 'संदेश',
            date: 'तारीख',
            displayname: 'प्रदर्शन नाम',
            franchisename: 'फ़्रेंचाइज़ी नाम',
            correspondent: 'पत्राचार',
            status: 'स्थिति',

            course: 'पाठ्यक्रम',
            instructor: 'अध्यापक',

            note: 'नोट',
            useformtocontactus: 'कृपया हमसे संपर्क करने के लिए नीचे दिए गए फॉर्म का उपयोग करें।',

            itemsselected: 'कोई आइटम चयनित नहीं | एक आइटम चयनित | {n} आइटम चयनित',
        },

        //Placeholders
        plch: {
            enter: 'दर्ज करें',
            username: 'यूज़रनेम',
            password: 'पासवर्ड',
            name: 'नाम',
            email: 'youremail@email.com',
            phone: '999-999-9999',
            country: 'भारत',
            Address: 'पता',
            line1: 'HNo 5-5-515/1/5, विद्यानगर',
            line2: '',
            city: 'हैदराबाद',
            state: 'तेलंगाना',
            zip: '1234',
            announcement: 'स्कूल घोषणा लिखें',
            classworkdesc: 'क्लासरूम कार्य विवरण'
        },

        //Messages and Error Messages
        msg: {
            pageunderdev: 'इस पृष्ठ पर आने के लिए धन्यवाद, यह विकास के अधीन है।',
            contactustodonate: 'यदि आप दान करना चाहते हैं, तो कृपया हमसे संपर्क करें',

            loading: 'लोड हो रहा है...' ,
            nothing: 'यहां दिखाने के लिए कुछ नहीं है।' ,
            allrights: 'सभी अधिकार सुरक्षित',
        },
        error: {
            passwordnotmatching: 'पासवर्ड और कन्फर्म पासवर्ड मेल नहीं खाता है।',
        },
        option: {
            defaulttheme: "डिफ़ॉल्ट थीम",
            darktheme: "डार्क थीम",
        },

        toast: {
            added: "सफलतापूर्वक जोड़ा गया!!",
            updated: "सफलतापूर्वक अद्यतन किया गया!!",
            archived: "सफलतापूर्वक संग्रहीत किया गया",
        },
        //Its same in all locale files so no need to add in other local files
        localeoption: {
            english: 'English',
            telugu: 'తెలుగు',
            spanish: 'Español',
            hindi: 'हिंदी',
        },

        SCHOOLNAME: 'स्कूल का नाम',
        copyright: 'कॉपीराइट © 2020 @:appname . सभी अधिकार सुरक्षित.',
        items: 'कोई आइटम नहीं | एक आइटम | {count} आइटम',
        linked: '@:message.hello, @:nav.ourmission में आपका स्वागत है, आपकी आईडी सफलतापूर्वक बनाई गई है',
};
export default messages
