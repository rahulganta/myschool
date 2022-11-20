//  Translated Locale Messages to Telugu
const messages = {
    appname: 'మై స్కూల్',
    nav: {
        home: 'హోమ్',
        mycourses: 'నా కోర్సులు',
        messages: "సందేశాలు",
        staff: 'సిబ్బంది',
        students: 'విద్యార్థులు',
        attendance: 'హాజరు',
        reports: 'రిపోర్ట్స్',
        schedule: 'షెడ్యూల్',
        aboutus: 'About Us',
        test: 'టెస్ట్'
    },

    // Buttons and Links
    btn: {
        submit: 'సమర్పించండి',
        cancel: 'రద్దు చేయు',
        close: 'క్లోజ్',
        confirm: 'నిర్ధారించు',
        save: 'SAVE',
        here: 'ఇక్కడ',
        add: 'జోడించు',
        edit: 'సవరించు',
        update: 'నవీకరించు',
        delete: 'తొలగించు',
        school: 'పాఠశాల',
        course: 'కోర్సు',
        user: 'యూసర్',
        teacher: 'టీచర్',
        admin: 'అడ్మిన్',

        login: 'లాగిన్',
        signout: 'సైన్ అవుట్',
        settings: 'సెట్టింగ్‌లు',
        userprofile: 'వినియోగదారు వివరాలు',
        contactsinfo: 'పరిచయాల సమాచారం',

        addschool: 'పాఠశాలను జోడించండి',
        viewschool: 'పాఠశాల చూడండి',
        editschool: 'పాఠశాలను సవరించండి',
        updateschool: 'పాఠశాలను నవీకరించండి',
        inactivateschool: 'INACTIVATE SCHOOL',

        addadmin: 'ADD ADMIN',
        editadmin: 'EDIT ADMIN',
        updateadmin: 'UPDATE ADMIN',
        adduser: 'ఆడ్ యూసర్',
        edituser: 'EDIT USER',
        updateuser: 'UPDATE USER',

        addcourse: 'ADD COURSE',
        editcourse: 'EDIT COURSE',
        updatecourse: 'UPDATE COURSE',
        viewcourse: 'VIEW COURSE',
        inactivatecourse: 'INACTIVATE COURSE',

        createclasswork: 'CREATE CLASS WORK',
        addfile: 'ADD FILE',

        addschoolannouncement: 'పాఠశాల ప్రకటనను జోడించండి',
    },

    //Page titles, card titles, headers, etc
    title: {
        schoollist: 'పాఠశాల జాబితా',
        schoolInfo: 'పాఠశాల సమాచారం',
        mymessages: 'నా సందేశాలు',
        schoolannouncements: 'పాఠశాల ప్రకటనలు',
        mycourses: 'నా కోర్సులు',

        missionstatement: 'మా లక్ష్య ప్రకటన',
        goal: 'మా లక్ష్యం',
        history: 'మా చరిత్ర',
        contactus: 'మమ్మల్ని సంప్రదించండి',
    },

    //Form Labels and other Labels
    label: {
        username: 'యూజర్ పేరు',
        name: 'పేరు',
        email: 'ఇమెయిల్',
        phone: 'ఫోన్',
        country: 'దేశం',
        address: 'చిరునామా',
        line1: 'Line 1',
        line2: 'Line 2',
        city: 'పట్టణం',
        state: 'రాష్ట్రం',
        zip: 'జిప్ / పోస్టల్ కోడ్',
        message: 'సందేశం',

        displayname: 'ప్రదర్శన పేరు',
        franchisename: 'ఫ్రాంచైజ్ పేరు',
        correspondent: 'కరస్పాండెంట్',
        status: 'స్టేటస్',

        course: 'కోర్సు',
        instructor: 'బోధకుడు',

        note: 'గమనిక',
        useformtocontactus: 'మమ్మల్ని సంప్రదించడానికి దయచేసి దిగువ ఫారమ్‌ను ఉపయోగించండి.',
    },

    //Placeholders
    plch: {
        enter: 'నమోదు చేయండి',
        username: 'వ్యక్తి పేరు',
        password: 'పాస్వర్డ్',
        name: 'పేరు',
        email: 'youremail@email.com',
        phone: '999-999-9999',
        country: 'ఇండియా',
        Address: 'చిరునామా',
        line1: 'హౌస్ నెంబర్ 5-5-515/1/5, విద్యానగర్',
        line2: '',
        city: 'హైదరాబాద్',
        state: 'తెలంగాణ',
        zip: '1234',
        contactmessage: 'మీరు మమ్మల్ని ఎందుకు సంప్రదిస్తున్నారో వ్రాయండి',
    },

    //Messages and Error Messages
    msg: {
        pageunderdev: 'ఈ పేజీని సందర్శించినందుకు ధన్యవాదాలు, ఈ పేజీ పురోగతిలో ఉంది.',
        contactustodonate: 'మీరు విరాళం ఇవ్వడానికి సిద్ధంగా ఉంటే, దయచేసి మమ్మల్ని సంప్రదించండి',
        loading: 'Loading...' ,
        nothing: 'Nothing to show here.' ,
        allrights: 'All Rights Reserved',
    },

    option: {
        defaulttheme: "డిఫాల్ట్ థీమ్",
        darktheme: "డార్క్ థీమ్",
    },

    copyright: 'కాపీ హక్కులు © 2020 @:appname .  అన్ని హక్కులూ ప్రత్యేకించుకోవడమైనది.',
    user: 'User | Users',
    items: 'No Items | one Item | {count} Items',
    linked: '@:message.hello, Welcome to @:appname, your id has been created successfully',
};
export const dateTimeFormats = {
    es: {
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
