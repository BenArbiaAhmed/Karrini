INSERT INTO category (id, name, description, image_url, is_active) VALUES
                                                                     (1, 'Programming and Development', 'Courses on various programming languages, web and mobile development.', 'https://example.com/images/programming.jpg', true),
                                                                     (2, 'Data Science', 'Explore the world of data analysis, machine learning, and artificial intelligence.', 'https://example.com/images/data_science.jpg', true),
                                                                     (3, 'Design', 'Courses on UI/UX, graphic design, and other creative design fields.', 'https://example.com/images/design.jpg', true),
                                                                     (4, 'Business and Marketing', 'Develop your skills in business, entrepreneurship, and digital marketing.', 'https://example.com/images/business.jpg', true),
                                                                     (5, 'IT and Cybersecurity', 'Learn about IT support, networking, and protecting systems from cyber threats.', 'https://example.com/images/cybersecurity.jpg', true),
                                                                     (6, 'Personal Development', 'Courses to improve your soft skills, productivity, and leadership abilities.', 'https://example.com/images/personal_dev.jpg', true),
                                                                     (7, 'Photography and Videography', 'Master the art of capturing stunning photos and creating compelling videos.', 'https://example.com/images/photography.jpg', true),
                                                                     (8, 'Health and Fitness', 'Courses on nutrition, exercise, yoga, and overall well-being.', 'https://example.com/images/fitness.jpg', true),
                                                                     (9, 'Music', 'Learn to play an instrument, produce music, or understand music theory.', 'https://example.com/images/music.jpg', true),
                                                                     (10, 'Languages', 'Expand your horizons by learning a new language.', 'https://example.com/images/languages.jpg', true);





INSERT INTO course (id, name, description, instructor_id, duration, price, learner_count , review_count, stars_count, level, image_url, category_id, is_deleted) VALUES
                                                                                                                                                    (1, 'Introduction to Python', 'A beginner-friendly introduction to the Python programming language, covering all the basic concepts.', 1, '10 hours', 49.99, 1500, 250, 1, 'BEGINNER', 'https://example.com/images/python_intro.jpg', 1, false),
                                                                                                                                                    (2, 'Advanced Java Concepts', 'Dive deep into advanced Java topics including concurrency, streams, and generics.', 2, '25 hours', 99.99, 800, 150, 2, 'ADVANCED', 'https://example.com/images/java_advanced.jpg', 1, false),
                                                                                                                                                    (3, 'Web Development with React', 'Learn to build modern, interactive web applications using the React library.', 3, '20 hours', 79.99, 2500, 400, 3, 'INTERMEDIATE', 'https://example.com/images/react_web_dev.jpg', 1, false),
                                                                                                                                                    (4, 'Data Science and Machine Learning Bootcamp', 'A comprehensive bootcamp covering data analysis, visualization, and machine learning algorithms.', 4, '50 hours', 199.99, 1200, 300, 4, 'ADVANCED', 'https://example.com/images/ds_ml_bootcamp.jpg', 2, false),
                                                                                                                                                    (5, 'UI/UX Design Fundamentals', 'An introduction to the principles of user interface and user experience design.', 5, '15 hours', 59.99, 3000, 500, 5, 'BEGINNER', 'https://example.com/images/ui_ux_design.jpg', 3, false),
                                                                                                                                                    (6, 'Digital Marketing Masterclass', 'Master the essential skills of digital marketing, including SEO, SEM, and social media marketing.', 6, '30 hours', 129.99, 1800, 350, 1, 'INTERMEDIATE', 'https://example.com/images/digital_marketing.jpg', 4, false),
                                                                                                                                                    (7, 'Introduction to SQL and Databases', 'Learn the fundamentals of SQL and how to manage relational databases.', 7, '12 hours', 39.99, 2200, 450, 2, 'BEGINNER', 'https://example.com/images/sql_intro.jpg', 1, false),
                                                                                                                                                    (8, 'Ethical Hacking for Beginners', 'Understand the basics of ethical hacking and penetration testing to secure systems.', 8, '22 hours', 149.99, 950, 180, 3, 'INTERMEDIATE', 'https://example.com/images/ethical_hacking.jpg', 5, false),
                                                                                                                                                    (9, 'Graphic Design with Adobe Illustrator', 'Learn to create stunning vector graphics and illustrations using Adobe Illustrator.', 9, '18 hours', 69.99, 1600, 280, 4, 'BEGINNER', 'https://example.com/images/illustrator_design.jpg', 3, false),
                                                                                                                                                    (10, 'Project Management Professional (PMP) Certification Prep', 'Prepare for the PMP certification exam with this comprehensive course.', 10, '40 hours', 249.99, 750, 200, 5, 'ADVANCED', 'https://example.com/images/pmp_prep.jpg', 6, false);

INSERT INTO lecture (id, title, description, display_order, duration, course_id)
VALUES
    (1, 'Introduction to Adobe Illustrator', 'Overview of the software and its interface.', 1, '00:15:00', 9),
    (2, 'Creating Basic Shapes', 'Learn how to create and manipulate basic shapes.', 2, '00:25:00', 9),
    (3, 'Using Pen and Pencil Tools', 'Master the pen and pencil tools for custom shapes.', 3, '00:30:00', 9);



INSERT INTO learning_material (id, lecture_id, material_type)
VALUES (1, 1, 'VIDEO');

INSERT INTO video_material (id, video_url)
VALUES (1, 'https://example.com/video_for_course_9_lecture_1.mp4');