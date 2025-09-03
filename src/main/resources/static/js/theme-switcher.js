document.addEventListener('DOMContentLoaded', function() {
    const toggleSwitch = document.querySelector('#checkbox');
    const currentTheme = localStorage.getItem('theme');
    const themeLabel = document.querySelector('.theme-label');

    // Применяем сохраненную тему
    if (currentTheme) {
        document.body.className = currentTheme;
        toggleSwitch.checked = currentTheme === 'dark-theme';
        updateThemeLabel();
    }

    // Обработчик переключения
    toggleSwitch.addEventListener('change', switchTheme, false);

    function switchTheme(e) {
        if (e.target.checked) {
            enableDarkTheme();
        } else {
            enableLightTheme();
        }
        updateThemeLabel();
    }

    function enableDarkTheme() {
        document.body.classList.remove('light-theme');
        document.body.classList.add('dark-theme');
        localStorage.setItem('theme', 'dark-theme');
    }

    function enableLightTheme() {
        document.body.classList.remove('dark-theme');
        document.body.classList.add('light-theme');
        localStorage.setItem('theme', 'light-theme');
    }

    function updateThemeLabel() {
        if (document.body.classList.contains('dark-theme')) {
            themeLabel.textContent = '☀'; // Солнце для светлой темы
            themeLabel.title = 'Переключить на светлую тему';
        } else {
            themeLabel.textContent = '☽'; // Луна для темной темы
            themeLabel.title = 'Переключить на темную тему';
        }
    }

    // Добавляем плавные переходы для всех элементов
    document.addEventListener('click', function(e) {
        if (e.target === toggleSwitch || e.target.closest('.theme-switch')) {
            // Добавляем класс анимации для плавного перехода
            document.body.classList.add('theme-changing');
            setTimeout(() => {
                document.body.classList.remove('theme-changing');
            }, 400);
        }
    });
});