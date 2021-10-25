<template>
    <v-container>
        <v-row class="mt-2">
            <v-col class="d-flex">
                <span class="text-h5">Publicaciones</span>
                <v-spacer></v-spacer>
                <v-btn text color="primary" class="mr-2">
                    Filtros
                    <v-icon right>mdi-arrow-down</v-icon>
                </v-btn>
                <v-btn color="primary">
                    Crear publicacion
                    <v-icon right>mdi-plus</v-icon>
                </v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col 
                v-for="publicacion in publicaciones" 
                :key="publicacion.id"
                class="col-12 col-sm-6 col-lg-4"
            >
                <v-card>
                    <v-card-title>Publicacion</v-card-title>
                    <v-card-text>Un poco de texto</v-card-text>
                    <v-card-actions>
                        <v-btn text color="primary">Ver mas</v-btn>
                    </v-card-actions>
                </v-card>
            </v-col>
        </v-row>
  </v-container>
</template>

<script>
export default {
    data: () => ({
        publicaciones: [
            {
                id: 1
            }, 
            {
                id: 2
            }, 
            {
                id: 3
            }
        ]
    })
}
</script>

<style>

</style>