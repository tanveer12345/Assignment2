

exports.homePage = (req, res, next) => {
    res.render('index', { title: 'Home' });
  };

exports.about = (req, res, next) => {
    res.render('about', { title: 'About',
     biodata: ''                       
    });
  };

  exports.projects = (req, res, next) => {
    res.render('projects', { title: 'Projects',
     biodata: ''                       
    });
  };

  exports.services = (req, res, next) => {
    res.render('services', { title: 'Services',
     biodata: ''                       
    });
  };

  exports.contact = (req, res, next) => {
    res.render('contact', { title: 'Contact',
     biodata: ''                       
    });
  };