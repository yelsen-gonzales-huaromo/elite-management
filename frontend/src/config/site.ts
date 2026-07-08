export const siteConfig = {
  name: import.meta.env.VITE_APP_NAME || 'Template React',
  shortName: import.meta.env.VITE_APP_SHORT_NAME || 'TemplateReact',
  description: import.meta.env.VITE_APP_DESCRIPTION || 'Sistema de gestión administrativa de alto rendimiento para empresas y estado.',
  url: import.meta.env.VITE_APP_URL || 'https://tu-dominio.com',
  ogImage: '/images/og-image.png',
  links: {
    twitter: import.meta.env.VITE_APP_TWITTER || 'https://twitter.com/tu-usuario',
    github: import.meta.env.VITE_APP_GITHUB || 'https://github.com/tu-repo',
  },
  author: import.meta.env.VITE_APP_AUTHOR || 'Tu Nombre / Empresa',
}

export type SiteConfig = typeof siteConfig
