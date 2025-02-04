import {Config} from "tailwindcss";

module.exports = {
    content: ["src/main/resources/templates/**/*.{html,js}"],
    theme: {
        extend: {},
    },
    daisyui: {
        themes: [
            {
                corporate: {
                    ...require("daisyui/src/theming/themes")["corporate"],
                    "primary": "#FF4893",
                    "primary-focus": "#e54084",
                    "primary-content": "#000",
                    "secondary": "#5FCEFF",
                    "secondary-focus": "#55b9e5",
                    "accent": "#FFB578",
                    "accent-focus": "#e5a26c",
                    "dark": "#4F46A3",
                    "light": "#FFFFFF"

                },
            },
        ],
    },
    plugins: [
        require("daisyui"),
        require("@tailwindcss/typography"),
        require("@tailwindcss/aspect-ratio"),
    ],
}