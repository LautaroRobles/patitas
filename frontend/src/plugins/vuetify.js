import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import colors from 'vuetify/es5/util/colors';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: "#B54F40",
                secondary: colors.lightBlue.base,
                accent: colors.orange.base,
                error: colors.red.base,
                warning: colors.amber.base,
                info: colors.blueGrey.base,
                success: colors.green.base,
                background: '#F4E9E3'
            },
            dark: {
                primary: "#B54F40"
            }
        },
        dark: false
    }
});
