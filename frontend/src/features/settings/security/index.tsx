import { ContentSection } from '../components/content-section'
import { SecurityContent } from './security-content'

export function SettingsSecurity() {
  return (
    <ContentSection
      title='Seguridad'
      desc='Gestiona la seguridad de tu cuenta y el historial de sesiones activas.'
    >
      <SecurityContent />
    </ContentSection>
  )
}
